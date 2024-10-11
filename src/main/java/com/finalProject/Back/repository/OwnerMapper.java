package com.finalProject.Back.repository;

import com.finalProject.Back.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerMapper {
    int save(User user);
}
