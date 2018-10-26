package com.example.demo.Service.Impl;

import com.example.demo.Bean.Portrait;
import com.example.demo.Dao.UploadDaoMapper;
import com.example.demo.Service.UploadServiceI;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.FileUtil;
import com.example.demo.utils.ImageCompression;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
@Service
public class UploadServiceImpl implements UploadServiceI {
    @Resource
    private UploadDaoMapper uploadDaoMapper;

    /**
     //     * 保存文件路径path，访问链接url,返回url。
     //     * @param file
     //     * @param request
     //     * @return String
     //     */
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
            if(contentType.indexOf("image")!=-1){
                uuidFileName = FileUtil.uploadFile(uuidFileName);
                ImageCompression.yasuo(path,filePath+"/",uuidFileName);//压缩图片删除原图
            }
            else if(contentType.indexOf("audo")!=-1){

            }
            else if(contentType.indexOf("video")!=-1){

            }

        }catch (Exception e){

        }
        url = "http://www.catoy.top:8085/showfile?path="+currentTime+"/"+uuidFileName+"&type="+contentType;
//        url = "localhost:8085/showfile?path="+currentTime+"/"+uuidFileName+"&type="+contentType;
        Portrait portrait = new Portrait();
        portrait.setFile_url(url);
        portrait.setFile_path(path);
        portrait.setFile_type(contentType);
        portrait.setFile_name(uuidFileName);
        uploadDaoMapper.saveFile(portrait);
        return portrait.getFile_url();
    }

    /**
     //     * 根据url返回id
     //     * @param url
     //     * @return id
     //     */
    @Override
    public String getIdByUrl(String url){
        return uploadDaoMapper.getIdByUrl(url);
    }

    /**
     //     * 根据id返回url
     //     * @param id
     //     * @return url
     //     */
    @Override
    public String getUrlById(String id){
        return uploadDaoMapper.getUrlById(id);
    }
}
