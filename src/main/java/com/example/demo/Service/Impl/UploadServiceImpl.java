package com.example.demo.Service.Impl;

import com.example.demo.Bean.Portrait;
import com.example.demo.Dao.UploadDaoMapper;
import com.example.demo.Service.UploadServiceI;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
@Service
public class UploadServiceImpl implements UploadServiceI {
//    @Value("${web.upload-path}")
//    private String filePath;

    @Resource
    private UploadDaoMapper uploadDaoMapper;

    @Override
    public String saveFile(MultipartFile file, HttpServletRequest request){
        String path = null;
        String url = null;
        String uuidFileName = null;
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String currentTime = DateUtil.getCurrentYear()+"/"+DateUtil.getCurrnetMonth()+"/"+DateUtil.getCurrnetDate();
        String filePath  = "/home/admin/card/" + currentTime;
//        String filePath  = "D:/tts/" + currentTime;
        try {
            uuidFileName = FileUtil.uploadFile(fileName);
            path = filePath+"/"+uuidFileName;
            url = "http://www.catoy.top:8085/showfile?path="+currentTime+"/"+uuidFileName+"&type="+contentType;
//            url = "localhost:8085/showfile?path="+currentTime+"/"+uuidFileName+"&type="+contentType;
        } catch (Exception e) {
            // TODO: handle exception
        }
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try{
            FileOutputStream out = new FileOutputStream(path);
            out.write(file.getBytes());
            out.flush();
            out.close();
        }catch (Exception e){

        }
        Portrait portrait = new Portrait();
        portrait.setFile_url(url);
        portrait.setFile_path(path);
        portrait.setFile_type(contentType);
        portrait.setFile_name(uuidFileName);
        uploadDaoMapper.saveFile(portrait);
        return portrait.getFile_url();
    }
}
