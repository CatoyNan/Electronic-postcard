//package com.example.demo.Service;
//
//import com.example.demo.Bean.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//public interface StudentServiceI {
//    List<Student> getALLStudentInfo();
//
//    void SaveOrUpdatestudent(Student student);
//
//    void deletestudent(String stuentNumber);
//
//    int getAllStudentcount();
//
//    int updateUserPwd(String account,String pwd);
//
//    Student login(String studentAcount, String password);
//
//    void SaveOrUpdateElementUser(Element element);
//
//    void deleteElemntUser(String elementNumber);
//
//    List<Element> getALLElementUser(String elmentuser);
//
//    int getALLElementUserRecordNumber();
//
//    void elementcharge(Element element);
//
//    Message SaveNewsInfo(News news,MultipartFile file,HttpServletRequest request);
//
//    int deleteNewsInfo(String news_id);
//
//    List<News> getALLNewsList();
//
//    int getAllNewsRecord();
//
//    List<CardCharge> getALLCardUserInfo(String stu_number);
//
//    int countAllCardUserInfo();
//
//    void SaveCardUser(CardCharge cardCharge);
//
//    void deleteCardUser(String stu_account);
//
//    void Cardcharge(CardCharge cardCharge);
//
//    Message SaveBookloanInfo(Book book,MultipartFile file,HttpServletRequest request);
//
//    int Savebook_back(String book_name,String stu_account);
//
//    List<Book> getALLBookUserInfo();
//
//    int getAllBookUserRecords();
//
//    List<Book> getStuBorrow_bookInfo(String stu_account);
//
//    int getStuBorrow_bookInfoRecords(String stu_account);
//
//    int delete_book_back(String stu_account);
//
//    int book_renewal(String stu_account, String book_name);
//}
