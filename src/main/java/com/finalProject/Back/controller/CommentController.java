package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqCommentDto;
import com.finalProject.Back.entity.Comment;
import com.finalProject.Back.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> write(@RequestBody ReqCommentDto.WriteDto dto) {
        commentService.write(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/comment/{boardId}")
    public ResponseEntity<?> getAll(@PathVariable Long boardId) {
        List<Comment> comments = commentService.getAll(boardId).getComments();
        System.out.println(comments);
        return ResponseEntity.ok().body(commentService.getAll(boardId));
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<?> modify(@RequestBody ReqCommentDto.ModifyDto dto) {
        commentService.modify(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().body(true);
    }
}
