package com.example.toto.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView list(
        @RequestParam HashMap<String, Object> map
    ) {
        ModelAndView mv = new ModelAndView();

        // System.out.println("키미노메뉴아디와? " + menu_id);

         // 총 글수
         int totalcount = boardService.getTotalCount(map);
        
         // 페이징
         int pagecount = 8;  //페이지당 출력할 갯수
         int totalpagecount = totalcount % pagecount == 0 ? totalcount / pagecount : totalcount / pagecount + 1;
         System.out.println("why?" + totalpagecount);
        
         int nowpage = Integer.parseInt((String)map.get("nowpage"));
        int startnum = (nowpage - 1) * pagecount + 1;
        int endnum = nowpage * pagecount;
        
        map.put("pagecount", pagecount);
        map.put("nowpage", nowpage);
        map.put("startnum", startnum);
        map.put("endnum", endnum);
         

        // 메뉴
        String menu_id = (String) map.get("menu_id");

        MenuVo menu = menuService.getMenu(menu_id);

        // BoardList - 게시글 목록
        List<BoardVo> boardList = boardService.getBoardList(map);

        mv.addObject("totalpagecount", totalpagecount);
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
    public ModelAndView write(
        @RequestParam HashMap<String, Object> map,
        HttpServletRequest request
            ) {

        String menu_id = (String) map.get("menu_id");
        String nowpage = (String) map.get("nowpage");
        System.out.println("나우페이지를" + nowpage);
        System.out.println("맵" + map);
        System.out.println("리퀘" + request);
        ModelAndView mv = new ModelAndView();
        if(menu_id.equals("MENU04")){
            mv.setViewName("redirect:/ImageBoard/List?nowpage=" + nowpage);
        } else{
            mv.setViewName("redirect:/Board/List?menu_id=" + menu_id + "&nowpage=" + nowpage);
        }

        // 게시글 등록
        if (menu_id.equals("MENU03")) {
            boardService.insertPdsBoard(map, request);
        } else {
            boardService.insertBoard(map);
        }

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

        ModelAndView mv = new ModelAndView();

        if(boardVo.getMenu_id().equals("MENU04")){
            mv.setViewName("redirect:/ImageBoard/List?nowpage=" + boardVo.getNowpage());
        } else{
            mv.setViewName("redirect:/Board/List?menu_id=" + boardVo.getMenu_id() + "&nowpage=" + boardVo.getNowpage());
        }

        boardService.updateBoard(boardVo);
        return mv;
    }

    // 게시글 삭제
    // ---------------------------------------------------------------------------
    @RequestMapping("/Board/Delete")
    public ModelAndView delete(String idx, String menu_id, String nowpage) {
        // System.out.println("idx는 뭐고? " + idx);
        ModelAndView mv = new ModelAndView();
        if(menu_id.equals("MENU04")){
            mv.setViewName("redirect:/ImageBoard/List?nowpage=" + nowpage);
        } else{
            mv.setViewName("redirect:/Board/List?menu_id=" + menu_id + "&nowpage=" + nowpage);
        }
        boardService.deleteBoard(idx);
        return mv;
    }

    // -----------------------------------------------------------------------------------------

    @RequestMapping("/ImageBoard/List")
    public ModelAndView imageBoard(
        @RequestParam HashMap<String, Object> map
    ) {
        ModelAndView mv = new ModelAndView("board/imageList");

        String menu_id = "MENU04";
        map.put("menu_id", menu_id);

        // 메뉴
        MenuVo menu = menuService.getMenu(menu_id);

        
        // 총 글수
        int totalcount = boardService.getTotalCount(map);
        
        // 페이징
        int pagecount = 8;  //페이지당 출력할 갯수
        int totalpagecount = totalcount % pagecount == 0 ? totalcount / pagecount : totalcount / pagecount + 1;
        System.out.println("why?" + totalpagecount);
        
        int nowpage = Integer.parseInt((String)map.get("nowpage"));
        int startnum = (nowpage - 1) * pagecount + 1;
        int endnum = nowpage * pagecount;
        
        map.put("pagecount", pagecount);
        map.put("nowpage", nowpage);
        map.put("startnum", startnum);
        map.put("endnum", endnum);
        
        // BoardList - 게시글 목록
        List<BoardVo> imageList = boardService.getBoardList(map);

        mv.addObject("totalpagecount", totalpagecount);
        mv.addObject("imageList", imageList);
        mv.addObject("menu", menu);
        // System.out.println("출력확인? " + totalcount);
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
    public Map<String, Object> deleteComment(@RequestBody CommentVo commentVo) {
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
    public Map<String, Object> updateComment(@RequestBody CommentVo commentVo) {
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
