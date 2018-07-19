package com.example.demo.Service.Impl;

import com.example.demo.Bean.Menu;
import com.example.demo.Dao.MenuDaoMapper;
import com.example.demo.Service.MenuserviceI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuserviceImpl implements MenuserviceI {
    @Resource
    private MenuDaoMapper menuDaoMapper;

    @Override
    public List<Menu> getAsyntree(String id) {
        List<Menu> list=new ArrayList<Menu>();
        if(id==null || id.equals("")){
            Menu menu=menuDaoMapper.getRootMenu();
            List<Menu> menus=menuDaoMapper.getAllnode(menu.getId());
            if(menus.size()>0){
                menu.setState("closed");
            }
            list.add(menu);
        }else{
            List<Menu> nodetrees=menuDaoMapper.getAllnode(id);
            for(int i=0;i<nodetrees.size();i++){
                Menu menu=nodetrees.get(i);
                List<Menu> nodemenus=menuDaoMapper.getAllnode(menu.getId());
                if(nodemenus.size()>0){
                    menu.setState("closed");
                }else{
                    menu.setState("open");
                }
                Map<String,Object> attributes=new HashMap<String,Object>();
                attributes.put("url",menu.getUrl());
                menu.setAttributes(attributes);
                list.add(menu);
            }
        }
        return list;
    }
}
