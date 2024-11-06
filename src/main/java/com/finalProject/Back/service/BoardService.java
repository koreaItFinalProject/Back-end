package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqBoardDto;
import com.finalProject.Back.dto.response.Board.RespBoardDto;
import com.finalProject.Back.entity.board.Board;
import com.finalProject.Back.entity.board.BoardLike;
import com.finalProject.Back.entity.board.BoardList;
import com.finalProject.Back.exception.NotFoundBoardException;
import com.finalProject.Back.repository.BoardLikeMapper;
import com.finalProject.Back.repository.BoardMapper;
import com.finalProject.Back.repository.CommentMapper;
import com.finalProject.Back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private BoardLikeMapper boardLikeMapper;
    @Autowired
    private CommentMapper commentMapper;

    public Long write(ReqBoardDto.WriteBoardDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Board board = dto.toEntity(principalUser.getId());
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
                "searchValue", dto.getSearchValue() == null ? "" : dto.getSearchValue(),
                "category", dto.getCategory()
        );
        List<BoardList> boardLists = boardMapper.getList(params);
        Integer boardTotalCount = boardMapper.getTotalCount();
        return RespBoardDto.RespBoardListDto.builder()
                .boards(boardLists)
                .totalCount(boardTotalCount)
                .build();
    }

    public RespBoardDto.RespBoardListDto getNoticeList(Long ownerId) {
        List<BoardList> boardLists = boardMapper.getNoticeList(ownerId);
        Integer boardTotalCount = boardMapper.getTotalCountByOwnerId(ownerId);
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
                .writerId(board.getWriterId())
                .nickname(board.getNickname())
                .img(board.getImg())
                .viewCount(board.getViewCount() + 1)
                .writeDate(board.getWriteDate())
                .build();
    }

    @Transactional(rollbackFor = SQLException.class)
    public void delete(Long boardId) {
        boardMapper.delete(boardId);
        commentMapper.deleteByBoardId(boardId);
        boardLikeMapper.deleteByBoardId(boardId);
    }

    public RespBoardDto.RespBoardLikeDto getLike(Long boardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if(!authentication.getName().equals("anonymousUser")) {
            PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
            userId = principalUser.getId();
        }
        BoardLike boardLike = boardLikeMapper.findByBoardIdAndUserId(boardId, userId);
        int likeCount = boardLikeMapper.getLikeCountByBoardId(boardId);
        return RespBoardDto.RespBoardLikeDto.builder()
                .boardLikeId(boardLike == null ? 0 : boardLike.getId())
                .likeCount(likeCount)
                .build();
    }

    public void like(Long boardId){
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        BoardLike boardLike = BoardLike.builder()
                .boardId(boardId)
                .userId(principalUser.getId())
                .build();
        boardLikeMapper.like(boardLike);
    }

    public void disLike(Long boardLikeId) {
        boardLikeMapper.disLike(boardLikeId);
    }
}
