package com.finalProject.Back.entity;

import com.finalProject.Back.security.principal.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
    private String nickname;
    private String img;

    public PrincipalUser toPrincipal() {
        return PrincipalUser.builder()
                .id(id)
                .username(username)
                .password(password)
//                .roles(userRoles)
                .build();
    }
}
