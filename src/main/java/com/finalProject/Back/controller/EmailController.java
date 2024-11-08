package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.email.ReqEmail;
import com.finalProject.Back.dto.request.email.ReqSendEmailDto;
import com.finalProject.Back.dto.response.email.RespEmailCheckDto;
import com.finalProject.Back.exception.EmailAlreadyExistsException;
import com.finalProject.Back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    private Map<String, String> verificationCodes = new HashMap<>();

    @PostMapping("/mail/send")
    public ResponseEntity<String> sendVerificationCode(@RequestBody ReqEmail emailRequest) {
        String email = emailRequest.getEmail();
        String verificationCode = generateVerificationCode();
        System.out.println(emailRequest);
        System.out.println(verificationCode);
        // 이메일 전송 로직
        try {
            sendEmail(email, verificationCode);
            return ResponseEntity.ok(verificationCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메일 전송 실패");
        }
    }

    @PostMapping("/mail/find/send/{email}")
        public ResponseEntity<String> sendEmailCode(@PathVariable String email, @RequestBody ReqSendEmailDto reqSendEmailDto) {
        System.out.println("이메일아디" + email);
        System.out.println("이메일" + reqSendEmailDto.getUsername());
        System.out.println("이메일" + reqSendEmailDto.getValue());
        if(reqSendEmailDto.getValue().equals("email")){
            try{
                sendUsername(email, reqSendEmailDto.getUsername());
                return ResponseEntity.ok("메일 전송 성공");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메일 전송 실패");
            }
        }

        return ResponseEntity.ok("ok");
    }

    private void sendEmail(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("회원 인증 코드");
        message.setText("인증 코드는 다음과 같습니다: " + verificationCode);

        javaMailSender.send(message);
    }

    private String generateVerificationCode() {
        // 인증 코드 생성 로직 (예: 6자리 랜덤 숫자)
        return String.valueOf(new Random().nextInt(999999));
    }

    private void sendUsername (String email ,String username) {
        System.out.println("아이디" +username);
        System.out.println("이메일" +email);
        System.out.println("메일 출발");
        SimpleMailMessage message =  new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("아이디 찾기 결과");
        message.setText("아이디는 다음과 같습니다: " + username);

        try{
            javaMailSender.send(message);
            System.out.println("메일 전송 성공");
        }catch (Exception e){
            System.out.println("메일 전송 실패");
        }
    }

    @GetMapping("/signup/check/{email}")
    public ResponseEntity<RespEmailCheckDto> checkEmailDuplicate(@PathVariable String email) {
        System.out.println("이메일 인증" + email);
        try {
            userService.checkEmailExists(email);
            System.out.println(email);
            RespEmailCheckDto response = RespEmailCheckDto.builder()
                    .email(email)
                    .exists(false)
                    .build();
            return ResponseEntity.ok(response);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(RespEmailCheckDto.builder()
                    .email(email)
                    .exists(true)
                    .build());
        }
    }
}