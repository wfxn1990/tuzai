package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {
    @Select("select * from sys_role")
    List<Role> findAll();

    @Insert("insert into sys_role values(role_seq.nextval,#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select r.* from sys_user_role ur,sys_role r where userId = #{userId} and ur.roleid = r.id")
    /*@Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(property = "permissionList",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findByRoleId", fetchType = FetchType.LAZY))
    })*/
    public List<Role> findByUserId(Integer userId);

    @Select("select * from sys_role where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(property = "permissionList",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findByRoleId",fetchType = FetchType.LAZY))
    })
    Role findById(Integer id);
    @Delete("delete from sys_role_permission where roleId =#{roleId}")
    void delPermissionsFromRole(Integer roleId);
    @Insert("insert into sys_role_permission(roleId,permissionId) values(#{param1},#{param2})")
    void addPermissionToRole(Integer roleId, Integer permissionId);
}
