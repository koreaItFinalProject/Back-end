package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespGetOwnerDto {
    private int id;
    private String cafeName;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
}
