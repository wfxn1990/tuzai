package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(Integer id);

    void addPermissionToRole(Integer roleId, Integer[] ids);

    PageInfo<Role> search(Integer page, Integer rows);

}
