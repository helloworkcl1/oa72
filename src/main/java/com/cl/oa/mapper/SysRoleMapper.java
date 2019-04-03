package com.cl.oa.mapper;

import com.cl.oa.dao.IBaseDao;
import com.cl.oa.entity.SysRole;

import java.util.List;

public interface SysRoleMapper extends IBaseDao<SysRole> {

    List<SysRole> queryRolePageByCondition(SysRole sysRole);

    int updateRoleById(Long roleId);

    int batchDel(List<Long> idList);

    List<SysRole> queryAllRole();

    int delUserFormRole(Long userId, Long roleId);

    int batchAddUserToRole(List<Long> idList, Long roleId);
}