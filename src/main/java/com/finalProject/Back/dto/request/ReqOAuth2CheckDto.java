package com.finalProject.Back.dto.request;

import lombok.Data;

@Data
public class ReqOAuth2CheckDto {
    private String oauth2Name;
    private String provider;
}
