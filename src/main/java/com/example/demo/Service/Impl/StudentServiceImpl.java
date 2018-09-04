package com.example.demo.Service.Impl;

import com.example.demo.Bean.*;
import com.example.demo.Dao.*;
import com.example.demo.Service.StudentServiceI;
import com.example.demo.utils.Base64Util;
import com.example.demo.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentServiceI {

    @Resource
    private StudentDaoMapper studentDaoMapper;

    @Resource
    private ElementDaoMapper elementDaoMapper;

    @Resource
    private NewsDaoMapper newsDaoMapper;

    @Resource
    private CardChargeDaoMapper cardChargeDaoMapper;

    @Resource
    private BookDaoMapper bookDaoMapper;

    @Override
    public List<Student> getALLStudentInfo() {
        return studentDaoMapper.findAllstudentinfo();
    }

    @Override
    public void SaveOrUpdatestudent(Student student) {
        Student student1=studentDaoMapper.getStudent(student.getStudentNumber());
        if (student1!=null){
            studentDaoMapper.updatestudent(student);
        }else {
            studentDaoMapper.savestuent(student);
        }
    }

    @Override
    public void deletestudent(String stuentNumber) {
        studentDaoMapper.deletestudent(stuentNumber);
    }

    @Override
    public int getAllStudentcount() {
        return studentDaoMapper.getAllStudentcount();
    }

    @Override
    public Student login(String studentAcount, String password) {
        Student student=studentDaoMapper.getStudent(studentAcount);
        return student;
    }

    @Override
    public void SaveOrUpdateElementUser(Element element) {
        Element element1=elementDaoMapper.getElemntbynumber(element.getElementNumber());
        if(element1!=null){
            elementDaoMapper.updateElementUser(element);
        }else{
            element.setElementNumber(String.valueOf(Math.round(Math.random()*9*1000000)));
            elementDaoMapper.saveElemnetUser(element);
        }
    }

    @Override
    public int updateUserPwd(String account, String pwd){
        return studentDaoMapper.updatepassword(account,pwd);
    }

    @Override
    public void deleteElemntUser(String elementNumber) {
        elementDaoMapper.deleteElemntuser(elementNumber);
    }

    @Override
    public List<Element> getALLElementUser(String elmentuser) {
        List<Element> list=null;
        if(elmentuser!=null && !elmentuser.equals("")){
            list=new ArrayList<Element>();
            Element element=elementDaoMapper.getElemntbyStaynumber(elmentuser);
            list.add(element);
        }else{
            list=elementDaoMapper.findAllElemntUser();
        }
        return list;
    }

    @Override
    public int getALLElementUserRecordNumber() {
        return elementDaoMapper.countElemntUser();
    }

    @Override
    public void elementcharge(Element element) {
        double charge_moeny=Double.valueOf(element.getChargeMoeny());
        Element element1=elementDaoMapper.getElemntbyStaynumber(element.getElmentuser());
        double current_emeent=element1.getCurrentelement()+(charge_moeny*1.75);
        element.setCurrentelement(current_emeent);
        elementDaoMapper.updateElemnt(element);
    }

    @Override
    public Message SaveNewsInfo(News news,MultipartFile file,HttpServletRequest request) {
        Message message = Message.getInstancell();
        if(file.getOriginalFilename().equals("")){
            message.setSuccess(false);
            message.setMsg("新闻图片不能为空！");
            return message;
        }
        String path = request.getSession().getServletContext().getRealPath("/tempImage")+"/"+file.getOriginalFilename();
        File localfile = new File(path);
        if(!localfile.getParentFile().exists()){
            localfile.getParentFile().mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(localfile);
            fos.write(file.getBytes());
            byte [] newsImage=FileUtil.readFileByBytes(path);
            news.setNews_image("data:image/png;base64,"+Base64Util.encode(newsImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(news.getNews_id()!=null && !news.getNews_id().equals("")){
            news.setNews_date(new Date());
            if(newsDaoMapper.updateNews(news)>0){
                message.setSuccess(true);
                message.setMsg("保存成功!");
                return message;
            }else{
                message.setSuccess(false);
                message.setMsg("无法保存！");
                return message;
            }

        }else{
            news.setNews_date(new Date());
            newsDaoMapper.SaveNews(news);
            message.setSuccess(true);
            message.setMsg("保存成功！");
            return message;
        }
    }

    @Override
    public int deleteNewsInfo(String news_id) {
       return  newsDaoMapper.deleteNews(news_id);
    }

    @Override
    public List<News> getALLNewsList() {
        return newsDaoMapper.findAllNewsList();
    }

    @Override
    public int getAllNewsRecord() {
        return newsDaoMapper.getAllNewsRecordNumber();
    }

    @Override
    public List<CardCharge> getALLCardUserInfo(String stu_number) {
        if(stu_number!=null && !stu_number.equals("")){
            List<CardCharge> list=new ArrayList<CardCharge>();
            CardCharge cardCharge=cardChargeDaoMapper.getcardinfo(stu_number);
            list.add(cardCharge);
            return list;
        }else{
           return cardChargeDaoMapper.findAllCardInfo();
        }
    }

    @Override
    public int countAllCardUserInfo() {
        return cardChargeDaoMapper.countAllCardInfo();
    }

    @Override
    public void SaveCardUser(CardCharge cardCharge) {
        cardCharge.setCard_balance(Double.valueOf(cardCharge.getCharge_money()));
        cardCharge.setChargetime(new Date());
        cardChargeDaoMapper.saveCardinfo(cardCharge);
    }

    @Override
    public void deleteCardUser(String stu_account) {
        cardChargeDaoMapper.deleteCardInfo(stu_account);
    }

    @Override
    public void Cardcharge(CardCharge cardCharge) {
        CardCharge cardCharge1=cardChargeDaoMapper.getcardinfo(cardCharge.getStu_account());
        double current_balance=cardCharge1.getCard_balance()+Double.valueOf(cardCharge.getCharge_money());
        cardCharge.setCard_balance(current_balance);
        cardCharge.setChargetime(new Date());
        cardChargeDaoMapper.chargeFree(cardCharge);
    }

    /**
     * 保存图书信息，并自动计算归还日期
     * @param book
     * @param file
     * @param request
     * @return
     */
    @Override
    public  Message SaveBookloanInfo(Book book,MultipartFile file,HttpServletRequest request) {
        Message message=Message.getInstancell();
        Student student=studentDaoMapper.getStudent(book.getStu_account());
        if(student==null){
            message.setSuccess(false);
            message.setMsg("账号异常，无法借阅图书！");
            return message;
        }
        Book Stu_BookInfo=bookDaoMapper.getStudentInfo(book.getStu_account());
        int total=0;
        if(file.getOriginalFilename().equals("")){
           message.setSuccess(false);
           message.setMsg("借阅书籍图片不能为空！");
           return message;
        }
        String path=request.getSession().getServletContext().getRealPath("/tempImage")+"/"+file.getOriginalFilename();
        File localfile=new File(path);
        if(!localfile.getParentFile().exists()){
            localfile.getParentFile().mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(localfile);
            fos.write(file.getBytes());
            byte [] bookImage=FileUtil.readFileByBytes(path);
            book.setBook_image("data:image/png;base64,"+Base64Util.encode(bookImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Stu_BookInfo!=null){
            book.setBook_state(0);
            book.setBook_uid(Stu_BookInfo.getBook_uid());
            Calendar cl=Calendar.getInstance();
            book.setBook_out_time(cl.getTime());
            cl.add(Calendar.MONTH,3);
            book.setBook_int_time(cl.getTime());
            total=bookDaoMapper.saveBorrow_bookInfo(book);
        }else{
            book.setBook_uid(UUID.randomUUID().toString());
            book.setBook_state(0);
            Calendar cl=Calendar.getInstance();
            book.setBook_out_time(cl.getTime());
            cl.add(Calendar.MONTH,3);
            book.setBook_int_time(cl.getTime());
            total+=bookDaoMapper.SaveStuBook_Info(book.getStu_account(),book.getBook_uid());
            total+=bookDaoMapper.saveBorrow_bookInfo(book);
        }
        if(localfile.exists()){
            localfile.delete();
        }
        if(total>0){
            message.setSuccess(true);
            message.setMsg("图书借阅信息录入成功！");
        }else{
            message.setSuccess(false);
            message.setMsg("图书借阅信息录入失败！");
        }
        return message;
    }

    @Override
    public int Savebook_back(String book_name,String stu_account) {
        Book book=bookDaoMapper.getStudentInfo(stu_account);
        return bookDaoMapper.Savebook_back(book_name,book.getBook_uid());
    }

    @Override
    public int delete_book_back(String stu_account) {
        int total=0;
        Book book=bookDaoMapper.getStudentInfo(stu_account);
        List<Book> bookList=bookDaoMapper.getStuBorrow_bookInfo(stu_account);
        for(Book book1:bookList){
            if(book1.getBook_state()==0){
                return 1;
            }
        }
        total+=bookDaoMapper.delete_book_borrowRecord(book.getBook_uid());
        total+=bookDaoMapper.deleteStu_bookInfo(book.getStu_account());
        return total;
    }

    @Override
    public int book_renewal(String stu_account, String book_name) {
        Book book=bookDaoMapper.getStudentInfo(stu_account);
        Calendar cl=Calendar.getInstance();
        cl.add(Calendar.MONTH,3);
        book.setBook_int_time(cl.getTime());
        return bookDaoMapper.book_renewal(book.getBook_uid(),book_name,book.getBook_int_time());
    }

    @Override
    public List<Book> getALLBookUserInfo() {
        return bookDaoMapper.findAllUserBookInfo();
    }

    @Override
    public int getAllBookUserRecords() {
        return bookDaoMapper.getAllBookUserRecords();
    }

    @Override
    public List<Book> getStuBorrow_bookInfo(String stu_account) {
        return bookDaoMapper.getStuBorrow_bookInfo(stu_account);
    }

    @Override
    public int getStuBorrow_bookInfoRecords(String stu_account) {
        return bookDaoMapper.getStuBorrow_bookInfoRecords(stu_account);
    }
}
