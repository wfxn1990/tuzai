package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
@ResponseBody
public class PermissionController {
  
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/search")
    public PageInfo<Permission> search(Integer page, Integer rows) {
        PageInfo<Permission> permission = permissionService.search(page, rows);
        return permission;
    }


}
