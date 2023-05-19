package com.example.toto;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import com.example.toto.menu.service.MenuService;
import com.example.toto.menu.vo.MenuVo;

@Configuration
public class WebConfig implements ServletContextInitializer {
    @Autowired
    private MenuService menuService;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        List<MenuVo> menuList = menuService.getMenuList();
        System.out.println("콘피그 : " + menuList);
        servletContext.setAttribute("menuList", menuList);
    }
}
