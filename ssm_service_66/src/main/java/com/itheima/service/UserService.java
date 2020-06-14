package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysUser;


import java.util.List;

/**
 * 用户认证的接口对象 -- 必须继承安全框架提供的服务接口
 */
public interface UserService {

    List<SysUser> findAll();

    void save(SysUser user);

    boolean isUniqueUsername(String username);

    SysUser findById(Integer id);

    void dele(Integer id);

    void deleteByPrimaryKeys (Integer[] ids);

    void updateByPrimaryKeySelective(SysUser user);

    PageInfo<SysUser> search(Integer page, Integer rows);

    void addRolesToUser(Integer id, Integer[] ids);

    SysUser findSysUserByUserCode(String userCode);
}
