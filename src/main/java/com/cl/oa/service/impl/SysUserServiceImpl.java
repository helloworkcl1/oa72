package com.cl.oa.service.impl;

import com.cl.oa.common.SysResult;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cl.oa.common.Page;
import com.cl.oa.dao.IBaseDao;
import com.cl.oa.entity.SysMenu;
import com.cl.oa.entity.SysUser;
import com.cl.oa.mapper.SysUserMapper;
import com.cl.oa.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public IBaseDao<SysUser> getDao() {
        return sysUserMapper;
    }

    public PageInfo<SysUser> selectByCondition(Page page, SysUser sysUser) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> sysUserList =  sysUserMapper.selectByCondition(sysUser);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

    @Override
    public PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> sysUserList =  sysUserMapper.queryAuthUserByRoleId(roleId);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

    @Override
    public PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> sysUserList =  sysUserMapper.queryNoAuthUserByRoleId(roleId,userName);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

    @Override
    public SysUser checkLogin(SysUser sysUser) {
        //通过用户名查询用户对象（密码）
        SysUser currentUser = sysUserMapper.getUserByUserName(sysUser);
        if(currentUser!=null){
            //再判断密码
            if(sysUser.getUserPassword().equals(currentUser.getUserPassword())){
                //密码正确
                return currentUser;
            }
        }
        return null;
    }

    @Override
    public List<SysMenu> getMenuListByUserId(Long userId) {
        return sysUserMapper.getMenuListByUserId(userId);
    }

    @Override
    public SysUser getUserByName(String username) {
        return sysUserMapper.getUserByUserName(username);
    }


}
