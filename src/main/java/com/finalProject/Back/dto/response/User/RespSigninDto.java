package com.finalProject.Back.dto.response.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespSigninDto {
    private String expireDate;
    private String accessToken;
}
