package com.finalProject.Back.dto.response.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespModifyProfile {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String img;
    private String role;
    private String nickname;
}
