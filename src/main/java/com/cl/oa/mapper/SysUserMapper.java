package com.cl.oa.mapper;

import com.cl.oa.dao.IBaseDao;
import com.cl.oa.entity.SysMenu;
import com.cl.oa.entity.SysUser;

import java.util.List;

public interface SysUserMapper extends IBaseDao<SysUser> {
    //根据条件查询用户分页信息
    List<SysUser> selectByCondition(SysUser sysUser);
    //根据角色id查询该角色下授权的所有用户信息
    List<SysUser> queryAuthUserByRoleId(Long roleId);
    //查询该角色下没有授权的所有用户
    List<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName);
    //通过用户名称查询用户对象
    SysUser getUserByUserName(SysUser sysUser);
    //通过用户ID查询菜单集合
    List<SysMenu> getMenuListByUserId(Long userId);

    SysUser getUserByUserName(String username);
}