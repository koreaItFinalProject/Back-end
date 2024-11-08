package com.finalProject.Back.dto.response.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespUserInfoDto {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String img;
    private String role;
}
