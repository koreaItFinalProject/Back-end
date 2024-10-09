package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqSignupDto;
import com.finalProject.Back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> addSignup(@RequestBody ReqSignupDto dto) {
        log.info("{}", dto + "ok");
        userService.userSignup(dto);
        return ResponseEntity.ok().body(true);
    }
}
