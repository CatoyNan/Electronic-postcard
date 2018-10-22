package com.example.demo.utils;

import java.io.*;
import java.util.UUID;

/**
 * 文件读取工具类
 */
public class FileUtil {
    public static String uploadFile(String fileName) throws  Exception {
        //截取文件后缀
        int idx = fileName.lastIndexOf(".");
        String extention = fileName.substring(idx);
        //得到唯一文件名
        String uuidFileName = UUID.randomUUID().toString().replace("-","")+extention;
        return uuidFileName;
    }
}
