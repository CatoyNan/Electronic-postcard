package com.example.demo.Service.Impl;

import com.example.demo.Bean.Admin;
import com.example.demo.Dao.YhglDaoMapper;
import com.example.demo.Service.YhglServiceI;
import com.example.demo.utils.MD5keyBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YhglServiceImpl implements YhglServiceI {

    @Resource
    private YhglDaoMapper yhglDaoMapper;

    private MD5keyBean md5=new MD5keyBean();

    @Override
    public int SaveOrUpdateUser(Admin admin) {
        if(admin.getId()!=null && !admin.getId().equals("")){
            Admin admin1=yhglDaoMapper.getUserInfoById(admin.getId());
            admin1.setAddress(admin.getAddress());
            admin1.setAge(admin.getAge());
            admin1.setSex(admin.getSex());
            admin1.setName(admin.getName());
            admin1.setTelphone(admin.getTelphone());
            return yhglDaoMapper.updateUser(admin1);
        }else {
            admin.setPassword(md5.getkeyBeanofStr("123456"));
            return yhglDaoMapper.SaveUser(admin);
        }
    }

    @Override
    public List<Admin> GetAllUserInfo(String account) {
        return yhglDaoMapper.GetAllUserInfo(account);
    }

    @Override
    public int GetAllUserInfoTotals(String account) {
        return yhglDaoMapper.GetAllUserInfoTotals(account);
    }

    @Override
    public int deleteUser(String id) {
        return yhglDaoMapper.deleteUser(id);
    }

    @Override
    public int updateUserPwd(String account, String pwd) {
        return yhglDaoMapper.updateUserPwd(account,md5.getkeyBeanofStr(pwd));
    }
}
