package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.User.*;
import com.finalProject.Back.dto.response.User.RespModifyProfile;
import com.finalProject.Back.dto.response.User.RespSigninDto;
import com.finalProject.Back.dto.response.User.RespSignupDto;
import com.finalProject.Back.dto.response.User.RespUserInfoDto;
import com.finalProject.Back.entity.OAuth2User;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.exception.Oauth2NameException;
import com.finalProject.Back.exception.SignupException;
import com.finalProject.Back.repository.OAuth2UserMapper;
import com.finalProject.Back.repository.UserMapper;
import com.finalProject.Back.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private OAuth2UserMapper oAuth2UserMapper;

    @Transactional(rollbackFor = Exception.class)
    public RespSignupDto userSignup(ReqSignupDto dto) {
        User user = null;

        user = dto.toEntity(passwordEncoder);
        User foundUser = userMapper.findByUsername(user.getUsername());

        if(foundUser != null){
            if(dto.getUsername().equals(foundUser.getUsername())){
                throw new SignupException("중복된 아이디입니다.");
            }
            if (oAuth2UserMapper.existsByEmail(dto.getEmail())) {
                System.out.println("중복된 이메일입니다 " + dto.getEmail());
                throw new Oauth2NameException("중복된 이메일로 가입할 수 없습니다.");
            }
        }else {
            userMapper.save(user);
        }

        return RespSignupDto.builder()
                .user(user)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public RespSignupDto oauthSignup(ReqOAuth2SignupDto dto) {
        System.out.println("dto : "+dto.getUsername());
        User user = dto.toUser(passwordEncoder);
        System.out.println(user);
        User foundUser = userMapper.findByUsername(dto.getUsername());
        System.out.println("user : " +foundUser);
        User oauth = userMapper.findById(user.getId());
        System.out.println("oauth : "+oauth);
        OAuth2User toOauth = OAuth2User.builder()
                .userId(oauth.getId())
                .email(oauth.getEmail())
                .oAuth2Name(dto.getOauth2Name())
                .provider(dto.getProvider())
                .build();
        if(foundUser != null){
            if(dto.getUsername().equals(foundUser.getUsername())){
                System.out.println("username : "+userMapper.findByUsername(user.getUsername()).getUsername());
                throw new SignupException("중복된 아이디입니다.");
            }
        }else{
            try {
                userMapper.save(user);
                System.out.println("User saved successfully");
                oAuth2UserMapper.save(toOauth);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("User save failed");
            }
        }
        if (dto.getOauth2Name() != null) {
            if (oAuth2UserMapper.existsByOauth2Name(toOauth.getOAuth2Name())) {
                System.out.println("Oauth2Name exists: " + dto.getOauth2Name());
                throw new Oauth2NameException("중복된 아이디로 가입할 수 없습니다.");
            }
            if (oAuth2UserMapper.existsByEmail(toOauth.getEmail())) {
                System.out.println("Oauth2email exists: " + dto.getEmail());
                throw new Oauth2NameException("중복된 이메일로 가입할 수 없습니다.");
            }
        }else {
            oAuth2UserMapper.save(toOauth);
        }
        return RespSignupDto.builder()
                .user(user)
                .build();
    }

    public RespSigninDto generaterAccessToken (ReqSigninDto dto){
        User user = checkUsernameAndPassword(dto.getUsername(), dto.getPassword());
        return RespSigninDto.builder()
                .expireDate(jwtProvider.getExpireDate().toString())
                .accessToken(jwtProvider.generateAccessToken(user))
                .build();
    }

    private User checkUsernameAndPassword(String username, String password) {
        User user = userMapper.findByUsername(username);
        System.out.println(user);

        if(user == null) {
            throw new UsernameNotFoundException("사용자 정보를 다시 확인하세요.");
        }

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }

        return user;
    }

    public Boolean isDuplicateUsername(String username) {
        return Optional.ofNullable(userMapper.findByUsername(username)).isPresent();
    }

    public OAuth2User mergeSignin(ReqOAuth2MergeDto dto) {
    User user = checkUsernameAndPassword(dto.getUsername(), dto.getPassword());
        System.out.println(user);
    return OAuth2User.builder()
            .userId(user.getId())
            .oAuth2Name(dto.getOauth2Name())
            .provider(dto.getProvider())
            .build();
    }

    public RespUserInfoDto getUserInfo(Long id) {
        log.info("{}" , id);
        User user = userMapper.findById(id);
        return RespUserInfoDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .img(user.getImg())
                .role(user.getRole())
                .build();
    }

    public boolean checkUsername(String username) {
        // DB에서 username의 존재 여부 확인
        return !userMapper.existsByUsername(username); // true: 사용 가능, false: 중복
    }
    public boolean checkNickname(String nickname) {
        // DB에서 username의 존재 여부 확인
        return !userMapper.existsByNickname(nickname); // true: 사용 가능, false: 중복
    }

    public RespModifyProfile modifyProfile(ReqModifyProfile profile) {
        System.out.println("service : " +profile.getNickname());
        User user = profile.toEntity();
        System.out.println("user: " + user);
        System.out.println("nickname: " + user.getNickname());
        userMapper.update(user);

        return RespModifyProfile.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .img(user.getImg())
                .role(user.getRole())
                .build();
    }
}
