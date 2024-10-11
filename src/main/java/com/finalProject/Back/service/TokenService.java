package com.finalProject.Back.service;

import com.finalProject.Back.entity.User;
import com.finalProject.Back.exception.AccessTokenValidException;
import com.finalProject.Back.repository.UserMapper;
import com.finalProject.Back.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProvider jwtProvider;

    // accessToken에 대한 로그인 검사
    public Boolean isValidAccessToken(String bearerAccessToken) {
        try{
            String accessToken = jwtProvider.removeBearer(bearerAccessToken);
            Claims claims = jwtProvider.getClaims(accessToken);
            Long userId = ((Integer) claims.get("userId")).longValue();
            User user = userMapper.findById(userId);

            if(user == null) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            throw new AccessTokenValidException("AccessToken 유효성 검사 실패");
        }
        return true;
    }
}
