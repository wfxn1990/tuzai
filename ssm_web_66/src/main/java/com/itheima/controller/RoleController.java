package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/role")
@ResponseBody
public class RoleController {
  
    @Autowired
    RoleService roleService;

    @RequestMapping("/search")
    public PageInfo<Role> search(Integer page, Integer rows) {
        PageInfo<Role> user = roleService.search(page, rows);
        return user;
    }


}
