package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.User.ReqSigninDto;
import com.finalProject.Back.dto.response.RespGetOwnerDto;
import com.finalProject.Back.dto.response.RespGetUserDto;
import com.finalProject.Back.dto.response.User.RespSigninDto;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.repository.OwnerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;


    public List<RespGetUserDto> getUsers(){
        return ownerMapper.getUsers();
    }

    public List<RespGetOwnerDto> getOwners(){
        return ownerMapper.getOwners();
    }

    public int deleteUser(int id){
        return ownerMapper.deleteUser(id);
    }

    public int deleteCafe(int id){
        return ownerMapper.deleteCafe(id);
    }
}
