package com.finalProject.Back.repository;

import com.finalProject.Back.dto.response.RespInfoDto;
import com.finalProject.Back.dto.response.RespUserInfoDto;
import com.finalProject.Back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
    User findByOAuth2Id(Long id);
    User findByOAuth2Name(String username);
    User findById(Long id);
    RespUserInfoDto findUserInfoById(Long id);
    int findDuplicatedValue(@Param("fieldName") String fieldName, @Param("value") String value);
    int updateFieldValue(@Param("id") Long id, @Param("fieldName") String fieldName, @Param("value") String value);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

    int update(User user);
    int imgUpdate(User user);

}
