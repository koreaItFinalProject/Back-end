package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespUserInfoDto {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String nickname;
    private String phoneNumber;
}
