package com.example.demo.Service.Impl;

import com.example.demo.Bean.Log;
import com.example.demo.Dao.LogDaoMapper;
import com.example.demo.Service.LogServiceI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogServiceI {

    @Resource
    private LogDaoMapper logDaoMapper;

    @Override
    public List<Log> findALLLogInfo() {
        return logDaoMapper.findALLLogInfo();
    }

    @Override
    public int findAllLogInfoTotals() {
        return logDaoMapper.findAllLogInfoTotals();
    }

    @Override
    public void insertLog(Log log) {
       logDaoMapper.insertLog(log);
    }

    @Override
    public int removeLog() {
      return  logDaoMapper.removeLog();
    }
}
