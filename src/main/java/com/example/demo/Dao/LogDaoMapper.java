package com.example.demo.Dao;

import com.example.demo.Bean.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogDaoMapper {

    public List<Log> findALLLogInfo();

    public int findAllLogInfoTotals();

    public void insertLog(Log log);

    public int removeLog();
}
