package com.example.demo.Dao;

import com.example.demo.Bean.Portrait;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadDaoMapper {
    public int saveFile(Portrait portrait);
    public String getIdByUrl(String file_url);
    public String getUrlById(String id);
}
