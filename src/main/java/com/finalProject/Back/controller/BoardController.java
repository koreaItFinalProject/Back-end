package com.finalProject.Back.controller;

import com.finalProject.Back.aspect.annotation.ValidAop;
import com.finalProject.Back.dto.request.ReqBoardDto;
import com.finalProject.Back.entity.Comment;
import com.finalProject.Back.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @ValidAop
    @PostMapping("/board")
    public ResponseEntity<?> write(@Valid @RequestBody ReqBoardDto.WriteBoardDto dto, BindingResult bindingResult) {
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

    @GetMapping("/board/{boardId}/like")
    public ResponseEntity<?> getLikes(@PathVariable Long boardId) {
        return ResponseEntity.ok().body(boardService.getLike(boardId));
    }

    @PostMapping("/board/{boardId}/like")
    public ResponseEntity<?> like(@PathVariable Long boardId) {
        boardService.like(boardId);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/board/like/{boardLikeId}")
    public ResponseEntity<?> disLike(@PathVariable Long boardLikeId) {
        boardService.disLike(boardLikeId);
        return ResponseEntity.ok().body(true);
    }

}
