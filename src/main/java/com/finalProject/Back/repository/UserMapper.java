package com.finalProject.Back.repository;

import com.finalProject.Back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
    User findByOAuth2Id(Long id);
    User findByOAuth2Name(String username);
    User findById(Long id);
    boolean existsByUsername(String username);
}
