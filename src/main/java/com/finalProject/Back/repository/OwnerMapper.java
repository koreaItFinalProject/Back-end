package com.finalProject.Back.repository;

import com.finalProject.Back.dto.response.RespGetOwnerDto;
import com.finalProject.Back.dto.response.RespGetUserDto;
import com.finalProject.Back.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OwnerMapper {
    List<RespGetUserDto> getUsers();
    List<RespGetOwnerDto> getOwners();
    int deleteUser(int id);
}
