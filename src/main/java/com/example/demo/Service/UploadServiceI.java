package com.example.demo.Service;

import com.example.demo.Bean.Portrait;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UploadServiceI {
    String saveFile(MultipartFile file, HttpServletRequest request);
    String getIdByUrl(String url);
    String getUrlById(String id);
}
