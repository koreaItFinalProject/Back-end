package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqCommentDto;
import com.finalProject.Back.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> write(@RequestBody ReqCommentDto.ReqWriteCommentDto dto) {
        commentService.write(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/comment/{boardId}")
    public ResponseEntity<?> getAll(@PathVariable Long boardId) {
        return ResponseEntity.ok().body(commentService.getAll(boardId));
    }
}
