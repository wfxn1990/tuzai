package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] ids) {
        roleDao.delPermissionsFromRole(roleId);
        if (ids != null) {
            for (Integer permissionId : ids) {
                roleDao.addPermissionToRole(roleId, permissionId);
            }
        }
    }

    @Override
    public PageInfo<Role> search(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        List<Role> List = roleDao.findAll();
        PageInfo<Role> pageInfo = new PageInfo<>(List,3);

        return pageInfo;
    }
}
