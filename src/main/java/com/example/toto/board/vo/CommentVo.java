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
public class CommentVo {
    private int cidx;
    private int idx;
    private String cwriter;
    private String comment_text;
    private String cindate;
}
