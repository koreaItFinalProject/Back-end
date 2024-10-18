package com.finalProject.Back.controller;

import com.finalProject.Back.aspect.annotation.ValidAop;
import com.finalProject.Back.dto.request.User.ReqOAuth2MergeDto;
import com.finalProject.Back.dto.request.User.ReqOAuth2SignupDto;
import com.finalProject.Back.dto.request.User.ReqSignupDto;
import com.finalProject.Back.entity.OAuth2User;
import com.finalProject.Back.exception.SignupException;
import com.finalProject.Back.service.OAuth2Service;
import com.finalProject.Back.service.TokenService;
import com.finalProject.Back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private OAuth2Service oAuth2Service;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ValidAop
    @PostMapping("/oauth/oauth2/merge")
    public ResponseEntity<?> oAuth2Merge(@Valid @RequestBody ReqOAuth2MergeDto dto, BindingResult bindingResult) {
        userService.mergeSignin(dto);
        if (bindingResult.hasErrors()) {
            // 유효성 검증 실패 시 에러 메시지 반환
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok().body(true);
    }

    @ValidAop
    @PostMapping("/oauth/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody ReqOAuth2SignupDto dto , BindingResult bindingResult) throws SignupException {
        System.out.println("컨트롤러" + dto);
        userService.oauthSignup(dto);
        return ResponseEntity.ok().body(true);
    }
}
