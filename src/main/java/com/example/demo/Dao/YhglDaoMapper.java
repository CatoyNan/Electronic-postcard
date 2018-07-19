package com.example.demo.Dao;

import com.example.demo.Bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YhglDaoMapper {
    Admin getUserInfoById(String id);

    int updateUser(Admin admin1);

    int SaveUser(Admin admin);

    List<Admin> GetAllUserInfo(@Param("account") String account);

    int GetAllUserInfoTotals(@Param("account") String account);

    int deleteUser(String id);

    int updateUserPwd(@Param("account") String account, @Param("pwd") String pwd);

    Admin adminlogin(@Param("account") String account);
}
