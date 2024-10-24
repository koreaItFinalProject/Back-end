package com.finalProject.Back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2User {
    private Long id;
    private Long userId;
    private String email;
    private String oAuth2Name;
    private String provider;
}
