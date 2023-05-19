package com.example.toto.user.vo;

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
public class UserVo {

    //Fields
    private String userid;
    private String passwd;	
    private String username;
    private String email;
    private String indate;

}
