package com.sr.shiro.controller;

import com.sr.shiro.bean.User;
import com.sr.shiro.service.QueryService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/5/12.
 */
@Controller
public class QueryController {
    @Autowired
    private QueryService queryService;

    @RequestMapping("/users")
    @ResponseBody
    public List<User> getUser(){
        return queryService.getAlluer();
    }


    /**
     * 登陆访问接口，从login.html表单提交过来
     * @param username
     * @param pwd
     */
    @RequestMapping("/loginService")
    public String login(String username,String pwd){
        String loginInfo = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        try {
            subject.login(token);
            loginInfo = "true";
            //jsonObject.put("token", subject.getSession().getId());
        } catch (IncorrectCredentialsException e) {
            loginInfo = "密码错误";
        } catch (LockedAccountException e) {
            loginInfo = "登录失败，该用户已被冻结";
        } catch (AuthenticationException e) {
            loginInfo = "该用户不存在";
        } catch (Exception e) {
            loginInfo = "e.printStackTrace();";
        }
        System.out.println("---------->>>>>>>>>>"+loginInfo);
        if("true".equals(loginInfo))
            return "/index.html";
        else{
            //如果错误退出登陆
            subject.logout();
            return "/login.html";
        }
    }


}
