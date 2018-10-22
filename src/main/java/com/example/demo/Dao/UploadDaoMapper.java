package com.example.demo.Dao;

import com.example.demo.Bean.Portrait;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadDaoMapper {
    public int saveFile(Portrait portrait);
}
