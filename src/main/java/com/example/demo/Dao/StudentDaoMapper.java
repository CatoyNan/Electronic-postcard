package com.example.demo.Dao;

import com.example.demo.Bean.Book;
import com.example.demo.Bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDaoMapper {

    public  void savestuent(Student student);

    public void deletestudent(String studentNumber);

    public void updatestudent(Student student);

    public int updatepassword(String account,String pwd);

    public List<Student> findAllstudentinfo();

    public Student getStudent(String studentNumber);

    int getAllStudentcount();
}
