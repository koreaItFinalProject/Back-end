package com.finalProject.Back.controller;

import com.finalProject.Back.aspect.annotation.ValidAop;
import com.finalProject.Back.dto.request.Token.ReqAccessDto;
import com.finalProject.Back.dto.request.User.ReqOAuth2MergeDto;
import com.finalProject.Back.dto.request.User.ReqOAuth2SignupDto;
import com.finalProject.Back.dto.request.User.ReqSigninDto;
import com.finalProject.Back.dto.request.User.ReqSignupDto;
import com.finalProject.Back.entity.OAuth2User;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.exception.SignupException;
import com.finalProject.Back.security.principal.PrincipalUser;
import com.finalProject.Back.service.OAuth2Service;
import com.finalProject.Back.service.TokenService;
import com.finalProject.Back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OAuth2Service oAuth2Service;

    @ValidAop
    @PostMapping("/user/signup")
    public ResponseEntity<?> addSignup(@RequestBody @Valid ReqSignupDto dto , BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.userSignup(dto));
    }

    @ValidAop
    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignIn(@RequestBody @Valid ReqSigninDto dto , BindingResult bindingResult){
        log.info("{}", dto);
        System.out.println("ok");
        return ResponseEntity.ok().body(userService.generaterAccessToken(dto));
    }

    @GetMapping("/auth/access")
    public ResponseEntity<?> access(ReqAccessDto dto) {
        return ResponseEntity.ok().body(tokenService.isValidAccessToken(dto.getAccessToken()));
    }

    @GetMapping("user/me")
    public ResponseEntity<?> getUserMe(){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        log.info("{}", principalUser);
        return ResponseEntity.ok().body(userService.getUserInfo(principalUser.getId()));
    }
}
