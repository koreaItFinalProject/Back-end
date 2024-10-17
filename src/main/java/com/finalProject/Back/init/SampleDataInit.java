package com.finalProject.Back.init;


import com.finalProject.Back.entity.User;
import com.finalProject.Back.entity.board.Board;
import com.finalProject.Back.repository.BoardMapper;
import com.finalProject.Back.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleDataInit implements CommandLineRunner {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            User user = User.builder()
                    .username("user" + (i + 1))
                    .password(passwordEncoder.encode("1q2w3e4r!!A"))
                    .name("김동" + (i + 1))
                    .email("user" + (i + 1) + "@naver.com")
                    .role("USER")
                    .nickname("writer" + i)
                    .phoneNumber("010-1243-5678")
                    .build();

            userMapper.save(user);
            users.add(user);
        }

        Random random = new Random();

        for(int i = 0; i < 436; i++) {
            int randomIndex = random.nextInt(20);
            boardMapper.save(Board.builder()
                    .writerId(users.get(randomIndex).getId())
                    .title("테스트 게시글 제목 " + i)
                    .content("<p>테스트</p>")
                    .build());
        }
    }
}
