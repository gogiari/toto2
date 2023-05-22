package com.example.toto.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.toto.board.service.BoardService;
import com.example.toto.board.vo.BoardVo;
import com.example.toto.board.vo.CommentVo;
import com.example.toto.menu.service.MenuService;
import com.example.toto.menu.vo.MenuVo;

@Controller
// @RequestMapping("/Board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/Board/List")
    public ModelAndView list(String menu_id) {
        ModelAndView mv = new ModelAndView();

        // System.out.println("키미노메뉴아디와? " + menu_id);

        // 메뉴
        MenuVo menu = menuService.getMenu(menu_id);

        // BoardList - 게시글 목록
        List<BoardVo> boardList = boardService.getBoardList(menu_id);

        mv.setViewName("board/list");
        mv.addObject("boardList", boardList);
        mv.addObject("menu", menu);
        // System.out.println("nope" + menuList);
        return mv;
    }

    @RequestMapping("/Board/WriteForm")
    public ModelAndView writeform(BoardVo boardVo) {
        ModelAndView mv = new ModelAndView("board/write");
        // 답글처리해야함

        return mv;
    }

    @RequestMapping("/Board/Write")
    public ModelAndView write(BoardVo boardVo) {
        ModelAndView mv = new ModelAndView();
        if (boardVo.getMenu_id().equals("MENU04")) {
            mv.setViewName("redirect:/ImageBoard/List");
        } else {
            mv.setViewName("redirect:/Board/List?menu_id=" + boardVo.getMenu_id());
        }

        // 게시글 등록
        boardService.insertBoard(boardVo);

        return mv;
    }

    @RequestMapping("/Board/View")
    public ModelAndView view(String idx) {
        ModelAndView mv = new ModelAndView("board/view");
        BoardVo boardVo = boardService.getBoard(idx);

        // 게시물 조회수증가
        boardService.incReadcount(idx);

        // 댓글
        List<CommentVo> commentList = boardService.getCommentList(idx);

        // System.out.println("받은보드" + boardVo);
        // System.out.println("받은댓글" + commentList);
        mv.addObject("boardVo", boardVo);
        mv.addObject("commentList", commentList);
        return mv;
    }

    // 게시글 수정
    // ---------------------------------------------------------------------------
    @RequestMapping("/Board/UpdateForm")
    public ModelAndView updateForm(String idx) {
        ModelAndView mv = new ModelAndView("board/update");
        BoardVo boardVo = boardService.getBoard(idx);
        // System.out.println("가져왓니??" + boardVo);
        mv.addObject("boardVo", boardVo);
        return mv;
    }

    @RequestMapping("/Board/Update")
    public ModelAndView update(BoardVo boardVo) {

        ModelAndView mv = new ModelAndView(
                "redirect:/Board/View?idx=" + boardVo.getIdx() + "&menu_id=" + boardVo.getMenu_id());
        boardService.updateBoard(boardVo);
        return mv;
    }

    // 게시글 삭제
    // ---------------------------------------------------------------------------
    @RequestMapping("/Board/Delete")
    public ModelAndView delete(String idx, String menu_id) {
        // System.out.println("idx는 뭐고? " + idx);
        ModelAndView mv = new ModelAndView("redirect:/Board/List?menu_id=" + menu_id);
        boardService.deleteBoard(idx);
        return mv;
    }

    // -----------------------------------------------------------------------------------------

    @RequestMapping("/ImageBoard/List")
    public ModelAndView imageBoard() {
        ModelAndView mv = new ModelAndView("board/imageList");

        String menu_id = "MENU04";

        // 메뉴
        MenuVo menu = menuService.getMenu(menu_id);

        // BoardList - 게시글 목록
        List<BoardVo> imageList = boardService.getBoardList(menu_id);

        mv.addObject("imageList", imageList);
        mv.addObject("menu", menu);
        // System.out.println("nope" + menuList);
        return mv;
    }

    @PostMapping(value = "/Board/WriteComment", consumes = "application/json", produces = "application/json")
    public @ResponseBody CommentVo writeComment(@RequestBody CommentVo commentVo) {
        int result = boardService.insertComment(commentVo);
        // System.out.println("결과" + result);
        if (result == 1) {
            return commentVo;
        } else {
            return null;
        }
    }

    @RequestMapping("/Board/CommentList")
    @ResponseBody
    public List<CommentVo> getCommentList(String idx) {
        List<CommentVo> commentList = boardService.getCommentList(idx);
        // System.out.println("받았니?" + commentList);
        return commentList;
    }

    @PostMapping("/Board/DeleteComment")
    @ResponseBody
    public Map<String,Object> deleteComment(@RequestBody CommentVo commentVo) {
        // System.out.println("댓글삭제" + commentVo);
        int result = boardService.deleteComment(commentVo);
        Map<String, Object> resultMap = new HashMap<>();
        if (result > 0) {
            resultMap.put("result", true);
        } else {
            resultMap.put("result", false);
        }

        return resultMap;
    }

    @PostMapping("/Board/UpdateComment")
    @ResponseBody
    public Map<String,Object> updateComment(@RequestBody CommentVo commentVo) {
        System.out.println("댓글수정" + commentVo);
        int result = boardService.updateComment(commentVo);
        Map<String, Object> resultMap = new HashMap<>();
        if (result > 0) {
            resultMap.put("result", true);
        } else {
            resultMap.put("result", false);
        }

        return resultMap;
    }

}
