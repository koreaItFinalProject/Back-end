package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqBoardDto;
import com.finalProject.Back.entity.Board;
import com.finalProject.Back.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public Long writeBoard(ReqBoardDto.WriteBoardDto dto) {
        Board board = dto.toEntity();
        boardMapper.save(board);
        return board.getId();
    }
}
