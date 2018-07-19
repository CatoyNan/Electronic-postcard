package com.example.demo.Config;

import com.example.demo.Bean.Admin;
import com.example.demo.Dao.YhglDaoMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiro extends AuthorizingRealm {

    @Resource
    private YhglDaoMapper yhglDaoMapper;

    /**
     * 权限获取
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陆认证，相当于底层操作
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account= (String) token.getPrincipal();
        Admin admin=yhglDaoMapper.adminlogin(account);
        if(admin==null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(admin,admin.getPassword(),ByteSource.Util.bytes(token.getCredentials()),
                getName());
        return simpleAuthenticationInfo;
    }
}
