package com.finalProject.Back.repository;

import com.finalProject.Back.entity.OAuth2User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OAuth2UserMapper {
    int save(OAuth2User oAuth2User);
}
