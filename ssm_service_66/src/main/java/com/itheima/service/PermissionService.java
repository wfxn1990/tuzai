package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    List<Permission> findParentPermission();

    void save(Permission permission);

    PageInfo<Permission> search(Integer page, Integer rows);
}
