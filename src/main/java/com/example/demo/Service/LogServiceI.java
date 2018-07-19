package com.example.demo.Service;

import com.example.demo.Bean.Log;

import java.util.List;

public interface LogServiceI {

    public List<Log> findALLLogInfo();

    public int findAllLogInfoTotals();

    public void insertLog(Log log);

    public int removeLog();
}
