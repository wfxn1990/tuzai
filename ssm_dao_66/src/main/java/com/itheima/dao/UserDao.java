package com.itheima.dao;

import com.itheima.domain.SysUser;
import com.itheima.domain.entity.PageResult;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {
    @Select("select * from sys_user where username = #{id} and status=1")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(property = "roleList",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findByUserId",fetchType = FetchType.LAZY))
    })
    SysUser findByUsername(String username);
    @Select("select * from sys_user")
    List<SysUser> findAll() ;
    @Insert("insert into sys_user (username,email,password,phoneNum,status) values(#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser user);
    @Select("select * from sys_user where id = #{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(property = "roleList",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findByUserId",fetchType = FetchType.LAZY))
    })
    SysUser findById(Integer id);
    @Delete("delete from sys_user where id = #{id}")
    void dele(Integer id);
    @Insert("insert into sys_user_role(userId,roleId) values(#{param1},#{param2})")
    void addRolesToUser(Integer userId, Integer roleId);

    void deleteByPrimaryKeys(Integer[] ids);

    void updateByPrimaryKeySelective(SysUser user);

    @Delete("delete from sys_user_role where userId=#{userId}")
    void delRolesFromUser(Integer userId);
}
