package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.User.ReqSigninDto;
import com.finalProject.Back.dto.response.User.RespSigninDto;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.repository.OwnerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;


}
