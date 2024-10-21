package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespGetUserDto {
    private int id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
}
