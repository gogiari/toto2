package com.example.toto.board.mapper;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.toto.board.vo.BoardVo;
import com.example.toto.board.vo.CommentVo;

@Repository
@Mapper
public interface BoardMapper {

    List<BoardVo> getBoardList(HashMap<String, Object> map);

    void insertBoard(HashMap<String, Object> map);

    BoardVo getBoard(String idx);

    void updateBoard(BoardVo boardVo);

    void deleteBoard(String idx);

    void incReadcount(String idx);

    List<CommentVo> getCommentList(String idx);

    int insertComment(CommentVo commentVo);

    int deleteComment(CommentVo commentVo);

    int updateComment(CommentVo commentVo);

    void insertPdsBoard(HashMap<String, Object> map, HttpServletRequest request);

    int getTotalCount(HashMap<String, Object> map);
}
