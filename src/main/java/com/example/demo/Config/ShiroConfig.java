package com.example.demo.Config;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> chainDefinitmap=new LinkedHashMap<String,String>();
        chainDefinitmap.put("/main","authc");
        chainDefinitmap.put("/**","anon");
        shiroFilterFactoryBean.setLoginUrl("/loginView");
        shiroFilterFactoryBean.setSuccessUrl("/main");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinitmap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiro myShiro(){
        MyShiro myShiro=new MyShiro();
        return myShiro;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(myShiro());
        return securityManager;
    }
}
