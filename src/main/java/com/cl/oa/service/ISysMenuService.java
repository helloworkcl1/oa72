package com.cl.oa.service;

import com.github.pagehelper.PageInfo;
import com.cl.oa.common.Page;
import com.cl.oa.common.SysResult;
import com.cl.oa.entity.SysMenu;
import com.cl.oa.entity.SysMenuParent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysMenuService extends IBaseService<SysMenu> {
    PageInfo<SysMenu> getMenuPageByCondition(Long roleId, Page page);

    PageInfo<SysMenu> queryMenuByCondition(Page page, SysMenu sysMenu);

    List<SysMenu> queryMenuList();

    SysResult addMenu(SysMenu sysMenu);

    SysMenuParent selectSysMenuParentByMenuId(Long menuId);

    SysResult updateMenu(SysMenu sysMenu);

    SysResult delMenuByMenuId(Long menuId);

    SysResult delMenuByidList(List<Long> idList);

    PageInfo<SysMenu> getNoAuthMenusByRoleId(Page page, Long roleId, String menuName);

    SysResult batchAddMenusToRole(List<Long> idList, Long roleId);

    SysResult delMenuToRole(Long menuId, Long roleId);
}
