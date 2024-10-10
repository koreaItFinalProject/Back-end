package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqBoardDto;
import com.finalProject.Back.dto.response.RespBoardDto;
import com.finalProject.Back.entity.board.Board;
import com.finalProject.Back.entity.board.BoardList;
import com.finalProject.Back.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public Long writeBoard(ReqBoardDto.WriteBoardDto dto) {
        Board board = dto.toEntity();
        boardMapper.save(board);
        return board.getId();
    }

    public RespBoardDto.RespBoardListDto getBoardList(ReqBoardDto.BoardListDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();
        List<BoardList> boardLists = boardMapper.findAllByStartIndexAndLimit(startIndex, dto.getLimit());
        Integer boardTotalCount = boardMapper.getTotalCount();
        return RespBoardDto.RespBoardListDto.builder()
                .boards(boardLists)
                .totalCount(boardTotalCount)
                .build();
    }
}
