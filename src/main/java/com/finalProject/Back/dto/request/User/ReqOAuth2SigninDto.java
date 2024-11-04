package com.finalProject.Back.dto.request.User;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqOAuth2SigninDto {
    @NotBlank(message = "사용자 이름을 입력해 주세요")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message = "OAuth2 이름을 입력해주세요")
    private String oauth2Name;
    @NotBlank(message = "제휴사명을 입력해주세요")
    private String provider;
    private String role;
}
