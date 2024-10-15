package com.finalProject.Back.controller;

import com.finalProject.Back.dto.request.ReqBoardDto;
import com.finalProject.Back.entity.Comment;
import com.finalProject.Back.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<?> write(@RequestBody ReqBoardDto.WriteBoardDto dto) {
        return ResponseEntity.ok().body(boardService.write(dto));
    }

    @GetMapping("/board/list")
    public ResponseEntity<?> getList(ReqBoardDto.BoardListDto dto) {
        return ResponseEntity.ok().body(boardService.getList(dto));
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> getDetail(@PathVariable Long boardId) {
        return ResponseEntity.ok().body(boardService.getDetail(boardId));
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<?> delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/board/{boardId}")
    public ResponseEntity<?> modify(@RequestBody ReqBoardDto.ModifyBoardDto dto) {
        return ResponseEntity.ok().body(boardService.modify(dto));
    }

}
