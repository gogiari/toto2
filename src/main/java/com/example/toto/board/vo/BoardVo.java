package com.example.toto.board.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardVo {
    private String idx;
    private String menu_id;
    private String title;
    private String cont;
    private String writer;
    private String regdate;
    private String readcount;
    private String bnum;
    private String lvl;
    private String step;
    private String nref;
    private String delnum;
    private String parent;

    private String menu_name;

    //댓글갯수
    private String ccnt;

    //페이징
    private int nowpage;
    private int pagecount;
    private int startnum;
    private int endnum;

    private int totalcount;
}
