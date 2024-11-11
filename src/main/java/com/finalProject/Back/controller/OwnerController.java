package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqBoardDto;
import com.finalProject.Back.dto.request.User.ReqSigninDto;
import com.finalProject.Back.security.principal.PrincipalUser;
import com.finalProject.Back.service.OwnerService;
import com.finalProject.Back.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OwnerController {

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/manager/user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok().body(ownerService.getUsers());
    }

    @GetMapping("/manager/owner")
    public ResponseEntity<?> getOwner() {
        return ResponseEntity.ok().body(ownerService.getOwners());
    }

    @DeleteMapping("/manager/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        System.out.println(id);
        return ResponseEntity.ok().body(ownerService.deleteUser(id));
    }

    @DeleteMapping("/manager/cafe/{id}")
    public ResponseEntity<?> deleteCafe(@PathVariable Long id) {
        System.out.println(id);
        return ResponseEntity.ok().body(ownerService.deleteCafe(id));
    }

    @GetMapping("/manager/info/{id}")
    public ResponseEntity<?> getInfo(@PathVariable Long id) {
        return ResponseEntity.ok().body(ownerService.getInfo(id));
    }

    @GetMapping("/user/auth/info")
    public ResponseEntity<?> getInfoUser() {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println(principalUser);
        return ResponseEntity.ok().body(ownerService.getInfo(principalUser.getId()));
    }

    @GetMapping("/owner/notice")
    public ResponseEntity<?> getNoticeList(ReqBoardDto.BoardListDto dto) {
        return ResponseEntity.ok().body(ownerService.getNoticeList(dto));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable Long id){
        System.out.println("삭제: "+id);
        return ResponseEntity.ok().body(ownerService.deleteUser(id));
    }

    @GetMapping("/owner/recent")
    public ResponseEntity<?> getRecent() {
        return ResponseEntity.ok().body(null);
    }
}
