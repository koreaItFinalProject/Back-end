package com.finalProject.Back.dto.response.email;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespEmailCheckDto {
    private String email;
    private boolean exists;
}