package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.Owner.ReqOwnerSignupDto;
import com.finalProject.Back.dto.response.Owner.RespOwnerSignupDto;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.repository.OwnerMapper;
import com.finalProject.Back.security.principal.PrincipalUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RespOwnerSignupDto ownerSignup(ReqOwnerSignupDto dto) {
        User user = null;

        user = dto.toEntity(passwordEncoder);
        ownerMapper.save(user);

        log.info("{}",user);
//
//        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//        User users = User.builder()
//                .id(principalUser.getId())
//                .build();
        return RespOwnerSignupDto.builder()
                .message("가입하신 이메일 주소를 통해 인증 후 사용할 수 있습니다.")
                .user(user)
                .build();
    }

}
