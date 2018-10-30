package com.example.demo.Controller;

import com.example.demo.Service.UploadServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


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
    public String toPloadPage(@RequestParam("type") String type, Model model){
        model.addAttribute("type",type);
        return "fileUpload";
    }
    /*
     *  处理文件上传
     */
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model){
        String url = uploadServiceI.saveFile(file,request);
        String id = uploadServiceI.getIdByUrl(url);
        if(url.length()>0 && id.length()>0){
            model.addAttribute("url",url);
            model.addAttribute("id",id);
            model.addAttribute("status",true);
            return "qrcode";
        }
        else {
            model.addAttribute("status",false);
            return "qrcode";
        }
    }
    /*
     *  显示文件
     */
    @RequestMapping(value = "/showfile",method = RequestMethod.GET)
    public String showfile(@RequestParam("path") String path, @RequestParam("type") String type, Model model){
        model.addAttribute("path",path);
        model.addAttribute("type",type);
        if(type.contains("image")){
            model.addAttribute("status","true");
            return "showImage";
        }
        else if(type.contains("audio")){
            model.addAttribute("status","true");
            return "showAudio";
        }
        else if(type.contains("video")){
            model.addAttribute("status","true");
            return "showVideo";
        }
        else {
            model.addAttribute("status","false");
            System.out.println("文件类型不符合要求");
            return "";
        }
    }

    /*
     *  工作人员根据用户id得到用户有二维码
     */
    @RequestMapping(value = "/getqrimageById",method = RequestMethod.GET)
    public String getqrimageById(@RequestParam("id") String id, Model model){
        String url = uploadServiceI.getUrlById(id);
//        System.out.println(url!=null);
        if(url != null){
            model.addAttribute("url",url);
            model.addAttribute("id",id);
            model.addAttribute("status","true");
            return "qrcode";
        }
        else {
            model.addAttribute("status","false");
            return "qrcode";
        }
    }
    /*
     *  跳转测试
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
