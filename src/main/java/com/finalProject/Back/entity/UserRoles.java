package com.finalProject.Back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoles {
    private Long id;
    private Long userId;
    private Long roleId; // role이랑 userRole은 1 대 1 매칭
    private Role role; // 1번일때 1번을 가져와야댐
}
