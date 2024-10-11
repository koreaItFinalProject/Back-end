package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqBoardDto;
import com.finalProject.Back.dto.response.RespBoardDto;
import com.finalProject.Back.entity.board.Board;
import com.finalProject.Back.entity.board.BoardList;
import com.finalProject.Back.exception.NotFoundBoardException;
import com.finalProject.Back.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public Long write(ReqBoardDto.WriteBoardDto dto) {
        Board board = dto.toEntity();
        boardMapper.save(board);
        return board.getId();
    }

    public Long modify(ReqBoardDto.ModifyBoardDto dto) {
        boardMapper.modify(dto.toEntity());
        return dto.getBoardId();
    }

    public RespBoardDto.RespBoardListDto getList(ReqBoardDto.BoardListDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();
        Map<String, Object> params = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "searchFilter", dto.getSearchFilter() == null || dto.getSearchFilter().isBlank() ? "all" : dto.getSearchFilter(),
                "searchValue", dto.getSearchValue() == null ? "" : dto.getSearchValue()
        );
        List<BoardList> boardLists = boardMapper.getList(params);
        Integer boardTotalCount = boardMapper.getTotalCount();
        return RespBoardDto.RespBoardListDto.builder()
                .boards(boardLists)
                .totalCount(boardTotalCount)
                .build();
    }

    public RespBoardDto.RespBoardDetailDto getDetail(Long boardId) {
        Board board = boardMapper.getDetail(boardId);
        if(board == null) {
            throw new NotFoundBoardException("게시글을 찾을 수 없습니다.");
        }
        boardMapper.addViewCountById(boardId);
        return RespBoardDto.RespBoardDetailDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount() + 1)
                .writeDate(board.getWriteDate())
                .build();
    }

    public void delete(Long boardId) {
        boardMapper.delete(boardId);
    }
}
