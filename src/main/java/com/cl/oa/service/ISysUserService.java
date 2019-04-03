package com.cl.oa.service;

import com.cl.oa.common.Page;
import com.cl.oa.common.SysResult;
import com.cl.oa.entity.SysMenu;
import com.cl.oa.entity.SysUser;
import com.cl.oa.entity.SysUser;
import com.github.pagehelper.PageInfo;


import java.util.List;


public interface ISysUserService extends IBaseService<SysUser>{

    PageInfo<SysUser> selectByCondition(Page page,SysUser sysUser);

    PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page);

    PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName, Page page);

    SysUser checkLogin(SysUser sysUser);

    List<SysMenu> getMenuListByUserId(Long userId);

    SysUser getUserByName(String username);
}
