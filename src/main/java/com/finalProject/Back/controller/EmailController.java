package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.email.ReqEmail;
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

    private String generateVerificationCode() {
        // 인증 코드 생성 로직 (예: 6자리 랜덤 숫자)
        return String.valueOf(new Random().nextInt(999999));
    }
    private void sendEmail(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("회원가입 인증 코드");
        message.setText("인증 코드는 다음과 같습니다: " + verificationCode);

        javaMailSender.send(message);
    }
}