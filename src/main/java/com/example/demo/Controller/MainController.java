package com.example.demo.Controller;

import com.example.demo.Service.UploadServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;


@Controller

public class MainController {
    @Autowired
    private UploadServiceI uploadServiceI;
    /*
     *  跳转到首页
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toIndex(){
        return "index";
    }

    /*
     *  跳转到上传页面
     */
    @RequestMapping(value = "/toPloadPage",method = RequestMethod.GET)
    public String toPloadPage(){
        return "fileUpload";
    }
    /*
     *  处理文件上传
     */
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model){
        String url = uploadServiceI.saveFile(file,request);
        if(url.length()>0){
            model.addAttribute("url",url);
            return "qrcode";
        }
        model.addAttribute("url","false");
       return "qrcode";

    }
    /*
     *  显示文件
     */
    @RequestMapping(value = "/showfile",method = RequestMethod.GET)
    public String showfile(@RequestParam("path") String path, @RequestParam("type") String type, Model model){
        try {
            model.addAttribute("path",path);
            model.addAttribute("type",type);
        }catch(Exception e){

        }
        return "showfile";
    }

    /*
     *  跳转测试
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
