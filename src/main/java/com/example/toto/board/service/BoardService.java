package com.example.toto.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toto.board.mapper.BoardMapper;
import com.example.toto.board.vo.BoardVo;

@Service("boardService")
public class BoardService {
    @Autowired
    private BoardMapper boardMappber;

    public List<BoardVo> getBoardList(String menu_id) {
        List<BoardVo> boardList = boardMappber.getBoardList(menu_id);
        return boardList;
    }

    public void insertBoard(BoardVo boardVo) {
        boardMappber.insertBoard(boardVo);
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

}
