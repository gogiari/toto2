package com.example.toto.user.conroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.toto.user.service.UserService;
import com.example.toto.user.vo.UserVo;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    
    @RequestMapping("/homeList")
    public ModelAndView homelist() {

        ModelAndView mv = new ModelAndView();

        List<UserVo> userList = userService.getUserList();

        mv.setViewName("user/homelist");
       
        mv.addObject("userList", userList);
        return mv;
    }

    
    @RequestMapping("/List")
    public ModelAndView list() {

        ModelAndView mv = new ModelAndView();

        List<UserVo> userList = userService.getUserList();

        mv.setViewName("user/list");
       
        mv.addObject("userList", userList);
        return mv;
    }
    
    public ModelAndView header() {

        ModelAndView mv = new ModelAndView();

        List<UserVo> userList = userService.getUserList();

        mv.setViewName("fragments/header");
       
        mv.addObject("userList", userList);
        return mv;
    }





    
    @RequestMapping("/WriteForm")
    public String writeForm() {
        return "user/write";
    }
    
    @RequestMapping("/Write")
    public ModelAndView write(UserVo userVo) {

        ModelAndView mv = new ModelAndView();

        userService.insertUser(userVo);

        mv.setViewName("redirect:/User/List");

        return mv;
    }
    @RequestMapping("View")
    public ModelAndView view(String userid){
        ModelAndView mv = new ModelAndView();
        UserVo vo = userService.getUser( userid );
        mv.setViewName("user/view");
        mv.addObject("user", vo);
        return mv;
    }
    @RequestMapping("Delete")
    public ModelAndView delete(String userid) {
        ModelAndView mv = new ModelAndView("redirect:/User/List");
        userService.deleteUser( userid );
        return mv;
    }
}
