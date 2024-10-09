package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class ReqSignupDto {
    private String userId;
    private String password;
    private String checkPassword;
    private String email;
    private String userName;
//    private String address;


    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(userId)
                .password(passwordEncoder.encode(password))
                .name(userName)
                .email(email)
//                .address(address)
                .build();
    }
}
