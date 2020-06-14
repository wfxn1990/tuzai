package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findParentPermission() {
        return permissionDao.findParentPermission();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public PageInfo<Permission> search(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        List<Permission> List = permissionDao.findAll();
        PageInfo<Permission> pageInfo = new PageInfo<>(List,3);

        return pageInfo;
    }
}
