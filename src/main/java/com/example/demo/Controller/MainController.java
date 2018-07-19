package com.example.demo.Controller;

import com.example.demo.Bean.Menu;
import com.example.demo.Service.MenuserviceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MenuserviceI menuserviceI;

    @RequestMapping("/loginview")
    public String loginview(){
        return "loginView";
    }

    @RequestMapping("/cardmanager")
    public String cardmanager(){
        return "schoolCardManager";
    }

    @RequestMapping("/bookmanager")
    public String bookmanager(){
        return "BookManager";
    }

    @RequestMapping("/getAsyntree")
    @ResponseBody
    private  List<Menu> getAsyntree(String id){
      List<Menu> list=menuserviceI.getAsyntree(id);
      return list;
    }
}
