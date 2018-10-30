package com.example.demo.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;

public class ImageCompression {
    public static boolean yasuo(String inputPath,String outputPath,String fileName)throws Exception{
        Thumbnails.of(inputPath)
                .size(400,500)
                .outputQuality(0.8f)
                .toFile(outputPath+fileName);
        File file = new File(inputPath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("图片删除成功！");
                return true;
            } else {
                System.out.println("删除图片失败！");
                return false;
            }
        } else {
            System.out.println("删除失败，图片不存在！");
            return false;
        }
    }
}
