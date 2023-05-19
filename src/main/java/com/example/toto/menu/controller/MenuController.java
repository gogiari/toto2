package com.example.toto.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.toto.menu.service.MenuService;
import com.example.toto.menu.vo.MenuVo;

@Controller
@RequestMapping("/Menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/List")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        List<MenuVo> menuList = menuService.getMenuList();
        mv.setViewName("menu/list");
        mv.addObject("menuList", menuList);
        return mv;
    }
    
    @RequestMapping("/WriteForm")
    public String wirteform() {
        return "menu/write";
    }
    @RequestMapping("/Write")
    public ModelAndView wirte(MenuVo menuVo) {
        ModelAndView mv = new ModelAndView();
        menuService.insertMenu(menuVo);
        mv.setViewName("redirect:/Menu/List");
        return mv;
    }

    @RequestMapping("/UpdateForm")
    public ModelAndView updateForm(String menu_id) {
        ModelAndView mv = new ModelAndView("menu/update");
        MenuVo menuVo = menuService.getMenu(menu_id);
        System.out.println("겟메뉴" + menuVo);
        mv.addObject("menuVo", menuVo);
        return mv;
    }

    @RequestMapping("/Update")
    ModelAndView update(MenuVo menuVo) {
        ModelAndView mv = new ModelAndView("redirect:/Menu/List");
        menuService.updateMenu(menuVo);
        return mv;
    }
    
    @RequestMapping("/Delete")
    ModelAndView delete(String menu_id) {
        System.out.println("메뉴삭제" + menu_id);
        ModelAndView mv = new ModelAndView("redirect:/Menu/List");
        menuService.deleteMenu(menu_id);
        return mv;
    }
}
