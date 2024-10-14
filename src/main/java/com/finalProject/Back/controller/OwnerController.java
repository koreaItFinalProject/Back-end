package com.finalProject.Back.controller;

import com.finalProject.Back.aspect.annotation.ValidAop;
import com.finalProject.Back.dto.request.Owner.ReqOwnerSignupDto;
import com.finalProject.Back.dto.request.User.ReqSigninDto;
import com.finalProject.Back.service.CafeService;
import com.finalProject.Back.service.OwnerService;
import com.finalProject.Back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private CafeService cafeService;

    @Autowired
    private UserService userService;
    @ValidAop
    @PostMapping("/owner/signup")
    public ResponseEntity<?> ownerSignup (@RequestBody ReqOwnerSignupDto dto , BindingResult bindingResult) {
        log.info("{}",dto);
        return ResponseEntity.ok().body(ownerService.ownerSignup(dto));
    };

    @PostMapping("/owner/signin")
    public ResponseEntity<?> ownerSignin (@RequestBody ReqSigninDto dto , BindingResult bindingResult) {
        return ResponseEntity.ok().body(userService.generaterAccessToken(dto));
    }

}
