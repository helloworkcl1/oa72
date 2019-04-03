package com.cl.oa.service;

import com.github.pagehelper.PageInfo;
import com.cl.oa.common.Page;
import com.cl.oa.common.SysResult;
import com.cl.oa.entity.SysRole;

import java.util.List;

public interface ISysRoleService extends IBaseService<SysRole> {
    PageInfo<SysRole> queryRolePageByCondition(Page page, SysRole sysRole);

    SysResult addRole(SysRole sysRole);

    SysResult update(SysRole sysRole);

    SysResult delRole(Long roleId);

    SysResult batchDel(List<Long> idList);

    List<SysRole> queryAllRole();

    SysResult batchAddUserToRole(List<Long> idList, Long roleId);

    SysResult delUserFormRole(Long userId, Long roleId);
}
