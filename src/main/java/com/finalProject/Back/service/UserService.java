package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqSignupDto;
import com.finalProject.Back.dto.response.RespSignupDto;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RespSignupDto userSignup(ReqSignupDto dto) {
        User user = null;

        user = dto.toEntity(passwordEncoder);
        userMapper.save(user);

        return RespSignupDto.builder()
                .message("가입하신 이메일 주소를 통해 인증 후 사용할 수 있습니다.")
                .user(user)
                .build();
    }
}
