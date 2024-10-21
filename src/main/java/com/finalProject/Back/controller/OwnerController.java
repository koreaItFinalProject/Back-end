package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.User.ReqSigninDto;
import com.finalProject.Back.service.OwnerService;
import com.finalProject.Back.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        System.out.println(id);
        return ResponseEntity.ok().body(ownerService.deleteUser(id));
    }
}
