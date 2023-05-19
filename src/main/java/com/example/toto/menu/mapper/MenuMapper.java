package com.example.toto.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.toto.menu.vo.MenuVo;

@Repository
@Mapper
public interface MenuMapper {

    List<MenuVo> getMenuList();

    void insertMenu(MenuVo menuVo);

    MenuVo getMenu(String menu_id);

    void deleteMenu(String menu_id);

    void updateMenu(MenuVo menuVo);

}
