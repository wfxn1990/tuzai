package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.UserDao;
import com.itheima.domain.SysUser;
import com.itheima.domain.entity.PageResult;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(SysUser user) {
        String password = user.getPassword();
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public void addRolesToUser(Integer id, Integer[] ids) {
        userDao.delRolesFromUser(id);
        if (ids != null) {
            for (Integer roleId : ids) {
                userDao.addRolesToUser(id, roleId);
            }
        }
    }

    @Override
    public SysUser findSysUserByUserCode(String userCode) {
        return userDao.findByUsername(userCode);
    }

    @Override
    public boolean isUniqueUsername(String username) {
        SysUser sysUser = userDao.findByUsername(username);
        return sysUser==null?true:false;
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void dele( Integer id) {
        //1.删除原有的关系
        /*for (Integer id : ids) {
               userDao.dele(id);
            }*/
        userDao.dele(id);
    }

    @Override
    public void deleteByPrimaryKeys(Integer[] ids) {
        userDao.deleteByPrimaryKeys(ids);
    }

    @Override
    public void updateByPrimaryKeySelective(SysUser user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageInfo<SysUser> search(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        List<SysUser> List = userDao.findAll();
        PageInfo<SysUser> pageInfo = new PageInfo<>(List,3);

        return pageInfo;
    }


}

