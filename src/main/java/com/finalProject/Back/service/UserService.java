package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.User.*;
import com.finalProject.Back.dto.response.User.*;
import com.finalProject.Back.entity.OAuth2User;
import com.finalProject.Back.entity.User;
import com.finalProject.Back.exception.EmailAlreadyExistsException;
import com.finalProject.Back.exception.Oauth2NameAlreadyExistsException;
import com.finalProject.Back.exception.Oauth2NameException;
import com.finalProject.Back.exception.SignupException;
import com.finalProject.Back.repository.OAuth2UserMapper;
import com.finalProject.Back.repository.UserMapper;
import com.finalProject.Back.security.jwt.JwtProvider;
import com.finalProject.Back.security.principal.PrincipalUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
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
            if (oAuth2UserMapper.existsByEmail(dto.getEmail()) || userMapper.findByEmail(dto.getEmail()) != null) {
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
        System.out.println(dto);

        // 이메일 중복 체크
        if (userMapper.findByEmail(dto.getEmail()) != null) {
            throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
        }

        // OAuth2 이름 중복 체크
        if (oAuth2UserMapper.findByOauth2Name(dto.getOauth2Name()) != null) {
            throw new Oauth2NameAlreadyExistsException("이미 사용 중인 OAuth2 이름입니다.");
        }


        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .nickname(dto.getNickname())
                .phoneNumber(dto.getPhoneNumber())
                .oauth(dto.getOauth2Name())
                .build();

        try {
            userMapper.save(user);
        } catch (Exception e) {
            throw new RuntimeException("회원가입 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
        }

        return RespSignupDto.builder()
                .user(user)
                .build();
    }

    public RespSigninDto generaterAccessToken(ReqSigninDto dto) {
        User user = checkUsernameAndPassword(dto);
        System.out.println("토큰 생성: " + user);
        return RespSigninDto.builder()
                .expireDate(jwtProvider.getExpireDate().toString())
                .accessToken(jwtProvider.generateAccessToken(user))
                .build();
    }

    private User checkUsernameAndPassword(ReqSigninDto dto) {
        User user = userMapper.findByUsername(dto.getUsername());
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }
        return user;
    }

    private User checkOAuth2UsernameAndPassword(ReqOAuth2SigninDto dto) {
        User user = userMapper.findByUsername(dto.getUsername());
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("사용자 정보를 다시 확인하세요");
        }
        return user;
    }

    public RespSigninDto  mergeSignin(ReqOAuth2SigninDto dto) {
        User user = checkOAuth2UsernameAndPassword(dto);
        System.out.println(user);
        OAuth2User existingOAuth2User = oAuth2UserMapper.findByUserIdAndProvider(user.getId(), dto.getProvider());
        if (existingOAuth2User == null) {
            // OAuth2 정보가 없으면 저장
            OAuth2User oAuth2User = OAuth2User.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .oAuth2Name(dto.getOauth2Name())
                    .provider(dto.getProvider())
                    .build();
            oAuth2UserMapper.save(oAuth2User);
        }
//        System.out.println("토큰 생성: " + user);
    return RespSigninDto.builder()
            .expireDate(jwtProvider.getExpireDate().toString())
            .accessToken(jwtProvider.generateAccessToken(user))
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

    public boolean isDuplicated(String fieldName, String value) {
        if(fieldName.equals("password")){
            String secretPassword = passwordEncoder.encode(value);
            System.out.println(secretPassword + " 1");
            return userMapper.findDuplicatedValue(fieldName, secretPassword) > 0;
        }
        return userMapper.findDuplicatedValue(fieldName, value) > 0; // true: 사용 가능, false: 중복
    }

    public Boolean isDuplicateUsername(String username) {
        return Optional.ofNullable(userMapper.findByUsername(username)).isPresent();
    }

    public Boolean modifyEachProfile(ReqModifyFieldDto dto){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if(dto.getFieldName().equals("password")){
            String passwordValue =passwordEncoder.encode(dto.getValue());
            System.out.println(passwordValue + " 2");
            return userMapper.updateFieldValue(principalUser.getId(), dto.getFieldName(), passwordValue) > 0;
        }
        return userMapper.updateFieldValue(principalUser.getId(), dto.getFieldName(), dto.getValue()) > 0;
    }

    public RespModifyProfile modifyProfileImg (User user) {
        userMapper.imgUpdate(user);
        return RespModifyProfile.builder()
                .id(user.getId())
                .img(user.getImg())
                .build();
    }

    public void checkEmailExists(String email) {
        if (userMapper.findByEmail(email) != null) {
            throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
        }
    }

    public RespUserIdDto FindByValue (String fieldName,String value){
        if(fieldName == "email") {
            User user = userMapper.findByEmail(value);
            if(user != null){
                return RespUserIdDto.builder()
                        .username(user.getUsername())
                        .value(value)
                        .build();
            }
        }
        if(fieldName == "username") {
            User user = userMapper.findByUsername(value);
            if (user != null) {
                return RespUserIdDto.builder()
                        .username(user.getUsername())
                        .value(value)
                        .build();
            }
        }
        return null;
    }
}
