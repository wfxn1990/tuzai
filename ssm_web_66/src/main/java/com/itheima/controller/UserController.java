package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;

import com.itheima.domain.entity.Result;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/findAll")
    public List findAll() {
        List<SysUser> list = userService.findAll();
        return list;
    }
    @RequestMapping("/search")
    public PageInfo<SysUser> search(Integer page, Integer rows) {
        PageInfo<SysUser> user = userService.search(page, rows);
        return user;
    }
    @RequestMapping("/add")
    public Result add(@RequestBody SysUser user) {
        userService.save(user);
        return new Result(true, "成功了");
    }
    @RequestMapping("/addRolesToUser")
    public Result addRolesToUser(@RequestParam Integer id,@RequestParam Integer[] ids) {
        userService.addRolesToUser(id,ids);
        return new Result(true, "成功了");
    }
    @RequestMapping("/del")
    public Result del(Integer[] ids) {
        try {
            //userService.dele(ids);
            userService.deleteByPrimaryKeys(ids);
            return new Result(true, "成功了");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, "失败");
    }

    @RequestMapping("/findOne")
    public List<SysUser> findOne(Integer id) {
        List list = new ArrayList();
        SysUser user = userService.findById(id);
        List<Role> roleList1= roleService.findAll();
        StringBuilder sb = new StringBuilder();
        for (Role role : user.getRoleList()) {
            sb.append(",");
            sb.append(role.getId());
            sb.append(",");
        }
        list.add(user.getId());
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getEmail());
        list.add(user.getPhoneNum());
        list.add(user.getStatus());
        list.add(user.getRoleList());
        list.add(roleList1);
        list.add(sb.toString());
        return list;
    }
    @RequestMapping("/findOne1")
    public void findOne1(Integer id) {
        ArrayList list = new ArrayList();
        SysUser user = userService.findById(id);
        Method[] sourceMethods = user.getClass().getMethods();
        for (int i = 1; i < sourceMethods.length-3; i++) {
            if (sourceMethods[i].getName().startsWith("get")) {
                try {
                    Object loValue = sourceMethods[i].invoke(user, null);  // 值
                    list.add(loValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
    @RequestMapping("/update")
    public Result update(@RequestBody SysUser user) {
        try {
            userService.updateByPrimaryKeySelective(user);
            /*userService.dele(user.getId());
            userService.save(user);*/
            return new Result(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
    }
}
