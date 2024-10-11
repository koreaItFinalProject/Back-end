package com.finalProject.Back.dto.response.User;

import com.finalProject.Back.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespSignupDto {
    private String message;
    private User user;
}
