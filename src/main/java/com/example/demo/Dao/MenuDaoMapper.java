package com.example.demo.Dao;

import com.example.demo.Bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDaoMapper {
    Menu getRootMenu();

    List<Menu> getAllnode(String pid);
}
