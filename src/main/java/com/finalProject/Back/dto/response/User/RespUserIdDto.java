package com.finalProject.Back.dto.response.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespUserIdDto {
    private String username;
    private String value;
}
