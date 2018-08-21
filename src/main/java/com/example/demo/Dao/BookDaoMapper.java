package com.example.demo.Dao;

import com.example.demo.Bean.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BookDaoMapper {

    public List<Book> findAllUserBookInfo();

    public List<Book> findAllUserBookInfobyaccount(String st_account);

    int saveBorrow_bookInfo(Book book);

    int SaveStuBook_Info(@Param("stu_account") String stu_account, @Param("book_uid") String book_uid);

    Book getStudentInfo(String stu_account);

    int getAllBookUserRecords();

    List<Book> getStuBorrow_bookInfo(String stu_account);

    int getStuBorrow_bookInfoRecords(String stu_account);

    int Savebook_back(@Param("book_name") String book_name, @Param("book_uid") String book_uid);

    int delete_book_borrowRecord(String book_uid);

    int deleteStu_bookInfo(String stu_account);

    int book_renewal(@Param("book_uid") String book_uid, @Param("book_name") String book_name,@Param("book_int_time") Date book_int_time);

    int getStuBookComment_Count(@Param("studentNumber") String studentNumber,@Param("book_name") String book_name);

    void setStu_source(@Param("studentNumber") String studentNumber,@Param("book_name") String book_name,@Param("stu_score") double stu_score);
}
