package com.finalProject.Back.repository;

import com.finalProject.Back.dto.response.Board.RespBoardInfoDto;
import com.finalProject.Back.entity.board.Board;
import com.finalProject.Back.entity.board.BoardList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int save(Board board);
    int modify(Board board);
    int delete(Long boardId);
    int deleteByUserId(Long userId);
    String getContent(Long boardId);
    Long findByBoardId(Long boardId);
    List<BoardList> getList(Map<String, Object> params);
    Board getDetail(Long boardId);
    int getTotalCount();
    int getTotalCountByOwnerId(Long userId);
    int addViewCountById(Long boardId);
    List<RespBoardInfoDto> getBoardInfoById(Long id);
    List<BoardList> getNoticeListByOwnerId(Map<String, Object> params);
    List<BoardList> getNoticeList(Long ownerId);
    List<Board> getRecent();
}
