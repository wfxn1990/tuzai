package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from sys_permission")
    List<Permission> findAll();
    @Select("select * from sys_permission where pid = 0 ")
    List<Permission> findParentPermission();
    @Insert("insert into sys_permission values(permission_seq.nextval,#{permissionName},#{url},#{pid})")
    void save(Permission permission);
    @Select("select p.* from sys_role_permission rp,sys_permission p  where rp.roleId = #{roleId} and rp.permissionid = p.id")
    public List<Permission> findByRoleId(Integer roleId);
}
