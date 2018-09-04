package com.example.demo.Controller;

import com.example.demo.Bean.*;
import com.example.demo.Service.StudentServiceI;
import com.example.demo.Service.YhglServiceI;
import com.example.demo.utils.DataGrid;
import com.example.demo.utils.MD5keyBean;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentMangerController {

    @Autowired
    private StudentServiceI studentServiceI;
    private MD5keyBean md5=new MD5keyBean();

    @Autowired
    private YhglServiceI yhglServiceI;

    private Message message=Message.getInstancell();
    private DataGrid dataGrid=DataGrid.getDataGrid();

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Message login(String account,String password,HttpSession session){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(account,md5.getkeyBeanofStr(password));
        if(!subject.isAuthenticated()){
            try {
                subject.login(token);
                Admin admin1= (Admin) subject.getPrincipal();
                session.setAttribute("admin",admin1);
                message.setSuccess(true);
                message.setMsg("登陆成功！");
            }catch (UnknownAccountException e){
              message.setSuccess(false);
              message.setMsg("账号出错！");
            }catch (IncorrectCredentialsException e){
                message.setSuccess(false);
                message.setMsg("密码错误！");
            }
        }
        return message;
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    @RequestMapping(value = "/loginView",method = RequestMethod.GET)
    public String loginView(){
        return "loginView";
    }

    @RequestMapping("/studentmanaer")
    public String studentmanaer(){
      return "StudentInfoManager";
    }

    @RequestMapping("/emengercy")
    public String emengercy(){
        return "ElementManager";
    }

    @RequestMapping("/NewsManager")
    public String NewsManager(){
        return "NewsManager";
    }

    @RequestMapping("/getALLStudentInfo")
    @ResponseBody
    public DataGrid getALLStudentInfo(int page,int rows){
        PageHelper.startPage(page,rows);
        List<Student> list=studentServiceI.getALLStudentInfo();
        int totals=studentServiceI.getAllStudentcount();
        dataGrid.setTotal(totals);
        dataGrid.setRows(list);
        return dataGrid;
    }

    @RequestMapping("/SaveOrUpdateStudent")
    @ResponseBody
    public Message SaveOrUpdatestudent(Student student){
        studentServiceI.SaveOrUpdatestudent(student);
        message.setSuccess(true);
        message.setMsg("记录编辑成功！");
        return message;
    }

    @RequestMapping("/deletestudent")
    @ResponseBody
    public  Message deletestudent(String studentNumber){
        studentServiceI.deletestudent(studentNumber);
        message.setSuccess(true);
        message.setMsg("记录删除成功！");
        return message;
    }

    @RequestMapping("/getALLElementUser")
    @ResponseBody
    public DataGrid getALLElementUser(String  elmentuser,int page,int rows){
        PageHelper.startPage(page,rows);
        List<Element> list=studentServiceI.getALLElementUser(elmentuser);
        int total=studentServiceI.getALLElementUserRecordNumber();
        dataGrid.setRows(list);
        dataGrid.setTotal(total);
        return dataGrid;
    }

    @RequestMapping("/SaveOrUpdateElementUser")
    @ResponseBody
    public Message SaveOrUpdateElementUser(Element element){
        studentServiceI.SaveOrUpdateElementUser(element);
        message.setSuccess(true);
        message.setMsg("记录新增成功！");
        return message;
    }

    @RequestMapping("/deleteElemntUser")
    @ResponseBody
    public  Message deleteElemntUser(String elementNumber){
        studentServiceI.deleteElemntUser(elementNumber);
        message.setSuccess(true);
        message.setMsg("记录删除成功！");
        return message;
    }

    @RequestMapping("/elementcharge")
    @ResponseBody
    public  Message elementcharge(Element element){
        studentServiceI.elementcharge(element);
        message.setSuccess(true);
        message.setMsg("缴费成功！");
        return message;
    }

    /**
     * 获取所有的新闻信息
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/getALLNewsList",method = RequestMethod.POST)
    @ResponseBody
    public DataGrid getALLNewsList(int page,int rows){
        PageHelper.startPage(page,rows);
        List<News> list=studentServiceI.getALLNewsList();
        int total=studentServiceI.getAllNewsRecord();
        dataGrid.setTotal(total);
        dataGrid.setRows(list);
        return dataGrid;
    }

    /**
     * 保存或更新发布的新闻信息
     * @param news
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/SaveNewsInfo",method = RequestMethod.POST)
    @ResponseBody
    public  Message SaveNewsInfo(News news,HttpServletRequest request,@RequestParam("newsImage") MultipartFile file){
        Message message=studentServiceI.SaveNewsInfo(news,file,request);
//        if(total>0){
//            message.setSuccess(true);
//            message.setMsg("新闻编辑成功！");
//        }else{
//            message.setSuccess(false);
//            message.setMsg("新闻编辑失败，请稍后重试！");
//        }
//        message.setMsg("成功");
//        message.setSuccess(true);
        return message;
    }

    /**
     * 删除发布的新闻信息
     * @param news_id
     * @return
     */
    @RequestMapping(value = "/deleteNewsInfo",method = RequestMethod.POST)
    @ResponseBody
    public  Message deleteNewsInfo(String news_id){
       int total=studentServiceI.deleteNewsInfo(news_id);
       if(total>0){
           message.setSuccess(true);
           message.setMsg("所选定的新闻信息删除成功！");
       }else{
           message.setSuccess(false);
           message.setMsg("所选定的新闻信息删除成功！");
       }
        return message;
    }

    @RequestMapping("/getALLCardUserInfo")
    @ResponseBody
    public DataGrid getALLCardUserInfo(String stu_number,int page,int rows){
        PageHelper.startPage(page,rows);
        List<CardCharge> list=studentServiceI.getALLCardUserInfo(stu_number);
        int total=studentServiceI.countAllCardUserInfo();
        dataGrid.setRows(list);
        dataGrid.setTotal(total);
        return dataGrid;
    }

    @RequestMapping("/SaveCardUser")
    @ResponseBody
    public Message SaveCardUser(CardCharge cardCharge){
        studentServiceI.SaveCardUser(cardCharge);
        message.setSuccess(true);
        message.setMsg("校园卡用户申请成功！");
        return message;
    }

    @RequestMapping("/deleteCardUser")
    @ResponseBody
    public Message deleteCardUser(String stu_account){
        studentServiceI.deleteCardUser(stu_account);
        message.setSuccess(true);
        message.setMsg("校园卡用户删除成功！");
        return message;
    }

    @RequestMapping("/Cardcharge")
    @ResponseBody
    public Message Cardcharge(CardCharge cardCharge){
        studentServiceI.Cardcharge(cardCharge);
        message.setSuccess(true);
        message.setMsg("校园卡充值成功！");
        return message;
    }

    /**
     * 获取所有学生借阅图书信息
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/getALLBookUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public DataGrid getALLBookUserInfo(int page,int rows){
        PageHelper.startPage(page,rows);
        List<Book> list=studentServiceI.getALLBookUserInfo();
        int totals=studentServiceI.getAllBookUserRecords();
        dataGrid.setTotal(totals);
        dataGrid.setRows(list);
        return dataGrid;
    }

    /**
     * 获取某个具体学生的书籍借阅历史
     * @param stu_account
     * @return
     */

    @RequestMapping(value = "/getStuBorrow_bookInfo",method = RequestMethod.POST)
    @ResponseBody
    public DataGrid getStuBorrow_bookInfo(String stu_account,int page,int rows){
        PageHelper.startPage(page,rows);
        List<Book> bookList=studentServiceI.getStuBorrow_bookInfo(stu_account);
        int total=studentServiceI.getStuBorrow_bookInfoRecords(stu_account);
        dataGrid.setRows(bookList);
        dataGrid.setTotal(total);
        return dataGrid;
    }

    /**
     * 保存学生图书借阅信息
     * @param book
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/SaveBookloanInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message SaveBookloanInfo(Book book,HttpServletRequest request,@RequestParam("bookImage") MultipartFile file){
        Message message=studentServiceI.SaveBookloanInfo(book,file,request);
        return message;
    }

    /**
     * 根据学生账号和书名归还相应的图书
     * @param book_name
     * @return
     */
    @RequestMapping("/book_back")
    @ResponseBody
    public Message book_back(String book_name,String stu_account){
        int total=studentServiceI.Savebook_back(book_name,stu_account);
        if(total>0){
            message.setSuccess(true);
            message.setMsg(book_name+"书籍归还成功！");
        }else{
            message.setSuccess(false);
            message.setMsg(book_name+"书籍归还失败，请稍微重试！");
        }
        return message;
    }

    /**
     * 删除学生具体的图书借阅记录
     * @param stu_account
     * @return
     */
    @RequestMapping(value = "/delete_book_back",method = RequestMethod.POST)
    @ResponseBody
    public Message delete_book_back(String stu_account){
        int total=studentServiceI.delete_book_back(stu_account);
        if(total==1){
            message.setSuccess(false);
            message.setMsg("该生还有未归还的书籍，无法清除借阅记录！");
        }else if(total>0 && total!=1){
            message.setSuccess(true);
            message.setMsg("学生借阅书籍记录消除成功！");
        }else{
            message.setSuccess(false);
            message.setMsg("学生借阅书籍记录消除失败，请稍后重试！");
        }
        return message;
    }

    /**
     * 图书续借服务（借期3个月）
     * @param stu_account
     * @param book_name
     * @return
     */
    @RequestMapping(value = "/book_renewal",method = RequestMethod.POST)
    @ResponseBody
    public Message book_renewal(String stu_account,String book_name){
        int total=studentServiceI.book_renewal(stu_account,book_name);
        if(total>0){
            message.setMsg(book_name+"书籍续借成功！");
            message.setSuccess(true);
        }else{
            message.setSuccess(false);
            message.setMsg(book_name+"书籍续借失败！");
        }
        return message;
    }
}
