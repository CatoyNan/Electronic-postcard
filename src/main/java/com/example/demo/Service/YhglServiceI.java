package com.example.demo.Service;

import com.example.demo.Bean.Admin;

import java.util.List;

public interface YhglServiceI {
    int SaveOrUpdateUser(Admin admin);

    List<Admin> GetAllUserInfo(String account);

    int GetAllUserInfoTotals(String account);

    int deleteUser(String id);

    int updateUserPwd(String account, String pwd);

}
