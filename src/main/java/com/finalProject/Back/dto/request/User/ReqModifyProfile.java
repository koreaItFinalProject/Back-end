package com.finalProject.Back.dto.request.User;

import com.finalProject.Back.entity.User;
import lombok.Data;

@Data
public class ReqModifyProfile {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String img;
    private String nickname;
    private String role;

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .name(name)
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .img(img)
                .role(role)
                .build();
    }
}
