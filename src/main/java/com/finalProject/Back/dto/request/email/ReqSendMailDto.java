package com.finalProject.Back.dto.request.email;

import lombok.Data;

@Data
public class ReqSendMailDto {
    private String email;
    private String code;
}