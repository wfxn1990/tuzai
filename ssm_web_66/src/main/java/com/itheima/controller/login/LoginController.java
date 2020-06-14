package com.itheima.controller.login;


import com.itheima.domain.entity.Result;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    //loginUrl指定的认证提交地址
    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws Exception{

        //如果登陆失败，则从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName=request.getParameter("shiroLoginFailure");
        //根据shiro返回的异常路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if(UnknownAccountException.class.getName().equals(exceptionClassName)){
                throw new Exception("账户不存在");
            }else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
                throw new Exception("用户名/密码错误");
            }else{
                throw new Exception();
            }
        }

        System.out.println("认证失败");
        return "index.html";
    }

}
