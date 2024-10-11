package com.finalProject.Back.dto.response.Owner;

import com.finalProject.Back.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespOwnerSignupDto {
    private String message;
    private User user;
}
