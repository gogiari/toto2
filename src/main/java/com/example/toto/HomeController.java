package com.example.toto;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.toto.user.service.UserService;
import com.example.toto.user.vo.UserVo;

@Controller
public class HomeController {
    private String backto;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home_start";
    }

    @GetMapping("/home")
    public String homepage() {
        return "home";
    }

    @GetMapping("/loginpage")
    public String loginpage(HttpServletRequest request) {
        backto = request.getHeader("Referer");
        return "loginpage";
    }

    @PostMapping("/loginthe")
    public ModelAndView loginthe(
            @RequestParam Map<String, Object> map, HttpSession session
            ) {
        ModelAndView mv = new ModelAndView();
        System.out.println("userid:" + map.get("userid"));
        System.out.println("passwd:" + map.get("passwd"));

        UserVo user = userService.getUserLog(map);
        // UserVo user = userService.getUser( map.get("userid") );

        System.out.println("유저:" + user);
        if (user != null) {
            session.setAttribute("user", user);
            mv.setViewName("redirect:/home");
            // System.out.println("이전페이지정보" + backto);
            mv.setViewName("redirect:" + (backto != null ? backto : "/home"));
        } else {
            mv.setViewName("loginpage");
        }

        return mv;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        String previousPage = request.getHeader("Referer");
        return "redirect:" + (previousPage != null ? previousPage : "/home");
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/userlist")
    public String userlist() {

        return "/user/list";
    }

    @RequestMapping("/fuck")
    public String fuck() {
        return "develmap";
    }
}
