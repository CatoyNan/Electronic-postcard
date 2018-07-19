package com.example.demo.Controller;

import com.example.demo.Bean.Log;
import com.example.demo.Bean.Message;
import com.example.demo.Service.LogServiceI;
import com.example.demo.utils.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogManagerController {

    @Autowired
    private LogServiceI logServiceI;

    private DataGrid dataGrid=DataGrid.getDataGrid();

    private Message message=Message.getInstancell();

    @RequestMapping(value = "/Log_index",method=RequestMethod.GET)
    public String Log_index(){
        return "Log";
    }

    @RequestMapping(value = "/GetALLLogRecordInfo",method = RequestMethod.POST)
    @ResponseBody
    public DataGrid GetALLLogRecordInfo(int page,int rows){
        List<Log> list=logServiceI.findALLLogInfo();
        int total=logServiceI.findAllLogInfoTotals();
        dataGrid.setTotal(total);
        dataGrid.setRows(list);
        return dataGrid;
    }

    @RequestMapping(value = "/clearLog",method = RequestMethod.POST)
    @ResponseBody
    public Message clearLog(){
        int totals=logServiceI.findAllLogInfoTotals();
        int toatl=logServiceI.removeLog();
        if(toatl==totals){
            message.setSuccess(true);
            message.setMsg("日志记录删除成功！");
        }else{
            message.setSuccess(false);
            message.setMsg("日志记录删除失败！");
        }
      return message;
    }
}
