package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class ReqSignupDto {

    private String username;
    private String password;
    private String checkPassword;
    private String name;
    private String email;
    private String nickname;


    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
