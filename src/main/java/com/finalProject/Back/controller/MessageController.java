package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqMessageDto;
import com.finalProject.Back.entity.Message;
import com.finalProject.Back.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class MessageController {

    private final Map<SseEmitter, Long> emitters = new ConcurrentHashMap<>();
    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<?> addNotice(@RequestBody ReqMessageDto dto) {
        // 알림 저장
        Long messageId = messageService.save(dto);

        // 알림 전송 대상 결정
        emitters.forEach((emitter, lastId) -> {
            System.out.println("messageId: " +messageId);
            try {
                    // 예시: emitter에 JSON 객체 형식으로 메시지 전송
                    emitter.send("{" +
                            "\"lastId\": " + messageId + ", " +
                            "\"type\": \"" + dto.getType() +
                            "\", \"content\": \"" + dto.getContent() +
                            "\"}\n\n");
            } catch (IOException e) {
                emitter.complete(); // 오류 발생 시 emitter 종료
                emitters.remove(emitter); // emitter 제거
            }
        });

        return ResponseEntity.ok().body("알림이 저장되었습니다.");
    }

    @GetMapping("/user/getMessage/{userId}")
    public ResponseEntity<?> getMessage(@PathVariable Long userId) {
        List<Message> messages = messageService.findByUserId(userId);
        return ResponseEntity.ok().body(messages);
    }

    @GetMapping(value = "/message/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamNotifications(@RequestParam(value = "lastId", required = false) Long lastId) {
        SseEmitter emitter = new SseEmitter(0L);  // 무제한 대기

        emitters.put(emitter, lastId);

        // 실시간 알림을 위해 주기적인 ping 전송
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                emitter.send(SseEmitter.event().name("ping").data("keep-alive"));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(emitter);  // 오류 발생 시 제거
            }
        }, 0, 15, TimeUnit.SECONDS);

        // 연결이 끊어진 경우 처리
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        return emitter;
    }
}