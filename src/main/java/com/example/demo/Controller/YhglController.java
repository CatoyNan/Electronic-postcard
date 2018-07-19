package com.example.demo.Controller;

import com.example.demo.Bean.Admin;
import com.example.demo.Bean.Log;
import com.example.demo.Bean.Message;
import com.example.demo.Service.LogServiceI;
import com.example.demo.Service.YhglServiceI;
import com.example.demo.utils.DataGrid;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class YhglController {

    private Message message=Message.getInstancell();
    private DataGrid dataGrid=DataGrid.getDataGrid();

    @Autowired
    private YhglServiceI yhglServiceI;

    @Autowired
    private LogServiceI logServiceI;

    private Log log=new Log();

    private Admin logadmin;

    @RequestMapping(value = "/yhgl_index",method = RequestMethod.GET)
    public String yhgl_index(){
        return "yhgl";
    }

    /**
     * 保存或更新用户信息
     * @param admin
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateUser",method = RequestMethod.POST)
    @ResponseBody
    public Message SaveOrUpdateUser(Admin admin, HttpServletRequest request){
        int total=yhglServiceI.SaveOrUpdateUser(admin);
        if(total>0){
            message.setSuccess(true);
            message.setMsg("用户信息更新成功！");
            log.setAuthor(logadmin.getName());
            log.setContent("更新了"+admin.getName()+"用户信息");
            log.setOperatetime(new Date());
            log.setRemote(request.getRemoteAddr());
            logServiceI.insertLog(log);
        }else{
            message.setMsg("用户信息更新失败！");
            message.setSuccess(false);
        }
        return message;
    }

    /**
     * 返回所有用户信息
     * @param page
     * @param rows
     * @param account
     * @return
     */
    @RequestMapping(value = "/GetAllUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public DataGrid GetAllUserInfo(int page,int rows,String account,HttpServletRequest request){
        logadmin= (Admin) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(page, rows);
        List<Admin> list=yhglServiceI.GetAllUserInfo(account);
        int total=yhglServiceI.GetAllUserInfoTotals(account);
        log.setAuthor(logadmin.getName());
        log.setContent("查询了所有用户信息");
        log.setOperatetime(new Date());
        log.setRemote(request.getRemoteAddr());
        logServiceI.insertLog(log);
        dataGrid.setRows(list);
        dataGrid.setTotal(total);
        return dataGrid;
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public Message deleteUser(String id,HttpServletRequest request){
        int total=yhglServiceI.deleteUser(id);
        if(total>0){
            message.setSuccess(true);
            message.setMsg("用户信息记录删除成功！");
            log.setAuthor(logadmin.getName());
            log.setContent("删除了id号为"+id+"用户信息");
            log.setOperatetime(new Date());
            log.setRemote(request.getRemoteAddr());
            logServiceI.insertLog(log);
        }else{
            message.setSuccess(false);
            message.setMsg("用户信息记录删除失败!");
        }
        return message;
    }

    /**
     * 修改用户密码
     * @param account
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/updateUserPwd",method = RequestMethod.POST)
    @ResponseBody
    public Message updateUserPwd(String account,String pwd,HttpServletRequest request){
        int total=yhglServiceI.updateUserPwd(account,pwd);
        if(total>0){
            message.setSuccess(true);
            message.setMsg("用户密码更新成功！");
            log.setAuthor(logadmin.getName());
            log.setContent("更新了账号为"+account+"用户密码");
            log.setOperatetime(new Date());
            log.setRemote(request.getRemoteAddr());
            logServiceI.insertLog(log);
        }else{
            message.setSuccess(false);
            message.setMsg("用户密码更新失败！");
        }
        return message;
    }
}
