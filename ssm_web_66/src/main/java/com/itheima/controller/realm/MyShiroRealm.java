package com.itheima.controller.realm;

/**
 * @program: tuzai
 * @description: 拦截器
 * @author: wfxn1990
 * @create: 2020-05-01 17:23
 **/
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import utils.MD5Util;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    public void setName(String name) {
        super.setName("MyShiroRealm");
    }

    /*
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roleNames = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        roleNames.add("admin");//添加角色
        permissions.add("index.html");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    /*
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

// 第一步从token中取出用户名
        String userCode = (String) token.getPrincipal();

        // 第二步：根据用户输入的userCode从数据库查询用户信息
        SysUser sysUser = null;
        try {
            sysUser = userService.findSysUserByUserCode(userCode);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // 如果查询不到返回null
        if(sysUser==null){
            return null;
        }

        // 从数据库查询到密码
        String password = sysUser.getPassword();
        //盐


        // 如果查询到,返回认证信息AuthenticationInfo
        //activeUser就是用户身份信息
        SysUser activeUser = new SysUser();
        activeUser.setUsername(sysUser.getUsername());
        //..

        //将activeUser设置simpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                activeUser, password,  this.getName());
        return simpleAuthenticationInfo;
    }


}

