package com.finalProject.Back.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Mapper
public interface OAuth2UserMapper {
    int save(OAuth2User oAuth2User);
}
