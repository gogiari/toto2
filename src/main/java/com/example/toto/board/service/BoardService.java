package com.example.toto.board.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toto.board.mapper.BoardMapper;
import com.example.toto.board.vo.BoardVo;
import com.example.toto.board.vo.CommentVo;

@Service("boardService")
public class BoardService {
    @Autowired
    private BoardMapper boardMappber;

    public List<BoardVo> getBoardList(HashMap<String, Object> map) {
        List<BoardVo> boardList = boardMappber.getBoardList(map);
        return boardList;
    }

    public void insertBoard(HashMap<String, Object> map) {
        boardMappber.insertBoard(map);
    }

    public BoardVo getBoard(String idx) {
        BoardVo boardVo = boardMappber.getBoard(idx);
        return boardVo;
    }

    public void updateBoard(BoardVo boardVo) {
        boardMappber.updateBoard(boardVo);
    }

    public void deleteBoard(String idx) {
        boardMappber.deleteBoard(idx);
    }

    public void incReadcount(String idx) {
        boardMappber.incReadcount(idx);
    }

    public List<CommentVo> getCommentList(String idx) {
        List<CommentVo> commentList = boardMappber.getCommentList(idx);
        return commentList;
    }

    public int insertComment(CommentVo commentVo) {
        return boardMappber.insertComment(commentVo);
        
    }

    public int deleteComment(CommentVo commentVo) {
        return boardMappber.deleteComment(commentVo);
    }

    public int updateComment(CommentVo commentVo) {
        return boardMappber.updateComment(commentVo);
    }

    public void insertPdsBoard(HashMap<String, Object> map, HttpServletRequest request) {
        boardMappber.insertPdsBoard(map, request);
    }

    public int getTotalCount(HashMap<String, Object> map) {
        int totalcount = boardMappber.getTotalCount(map);
        return totalcount;
    }

}
