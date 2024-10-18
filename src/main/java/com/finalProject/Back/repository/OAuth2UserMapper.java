package com.finalProject.Back.repository;

import com.finalProject.Back.entity.OAuth2User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface OAuth2UserMapper {
    int save(OAuth2User oAuth2User);
    boolean existsByOauth2Name(String oauth2Name);
}
