package com.finalProject.Back.controller;

import com.finalProject.Back.aspect.annotation.ValidAop;
import com.finalProject.Back.dto.request.Token.ReqAccessDto;
import com.finalProject.Back.dto.request.User.*;
import com.finalProject.Back.dto.response.User.RespModifyProfile;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.security.principal.PrincipalUser;
import com.finalProject.Back.service.OAuth2Service;
import com.finalProject.Back.service.TokenService;
import com.finalProject.Back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

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

    // 수정중
    @GetMapping("/user/duplicated/{fieldName}")
    public ResponseEntity<?> checkDuplicated(@PathVariable String fieldName, @RequestParam String value ){
        // 유효성 검사
        Map<String, String> regexMap = Map.of(
                "username", "^(?=.*[a-z])(?=.*\\d)[a-z0-9]{8,}$",
                "password", "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$",
                "name", "^[가-힣]+$",
                "email", "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
                "phoneNumber", "^010.{1,11}$"
        );
        Map<String, String> fieldNameMap = Map.of(
                "username", "사용자이름",
                "name", "이름",
                "nickname", "닉네임",
                "email", "이메일",
                "password","비밀번호",
                "phoneNumber", "전화번호"
        );
        // 정규식 검사
        if (regexMap.containsKey(fieldName) && !value.matches(regexMap.get(fieldName))) {
            return ResponseEntity.badRequest().body(Map.of(
                    "isError", true,
                    "errorField", fieldName,
                    "errorName", fieldName + " 형식 오류",
                    "errorMessage", "올바른 " + fieldNameMap.get(fieldName) + " 형식이 아닙니다."
            ));
        }

        if (userService.isDuplicated(fieldName, value)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "isError" , true,
                    "errorField", fieldName,
                    "errorName", fieldName + " 중복",
                    "errorMessage", "이미 사용중인 " + fieldNameMap.get(fieldName) + "입니다."
            )); // 중복일 경우
        }
        return ResponseEntity.ok(Map.of(
                "isError" , false,
                "errorField", "",
                "errorName", "",
                "errorMessage", ""
        ));
    }

    // 수정중
    @PutMapping("/user/info/{fieldName}")
    public ResponseEntity<?> modifyUser(@PathVariable String fieldName ,@RequestBody ReqModifyFieldDto dto) {
        if(!userService.modifyEachProfile(dto)) {
            return ResponseEntity.badRequest().body("수정 실패");
        }
        return ResponseEntity.ok().body("수정 성공");
    }

    @PutMapping("/mypage/profile/img")
    public ResponseEntity<?> modifyProfileImg (@RequestBody ReqImageDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = User.builder()
                .id(principalUser.getId())
                .img(dto.getImg())
                .build();

        userService.modifyProfileImg(user);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/user/find/duplicated/{fieldName}")
    public ResponseEntity<?> FindByCheckField(@PathVariable String fieldName, @RequestParam String value) {
        if (userService.isDuplicated(fieldName, value)) {
            return ResponseEntity.badRequest().body("중복");
        }
        return ResponseEntity.ok().body("사용 가능");
    }
}
