package com.cl.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.cl.oa.common.Page;
import com.cl.oa.common.SysResult;
import com.cl.oa.entity.SysMenu;
import com.cl.oa.entity.SysUser;
import com.cl.oa.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    //跳转到登陆页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /*@RequestMapping("/checkLogin")
    public String checkLogin(SysUser sysUser,ModelMap map){
        //登录认证
        SysUser currentUser = sysUserService.checkLogin(sysUser);
        if(currentUser==null){
            //登录失败
            return "login";
        }
        //登录成功后查询此登录用户能看到的菜单集合
        List<SysMenu> sysMenuList = sysUserService.getMenuListByUserId(currentUser.getUserId());
        map.put("menuList",sysMenuList);
        return "index";
    }*/


    //点击登录跳转用户登录页面
    @RequestMapping("/checkLogin")
    public String checkLogin(SysUser sysUser,ModelMap map){
        Subject currentUser=SecurityUtils.getSubject();

        if(!currentUser.isAuthenticated()){
            //登录认证
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(),sysUser.getUserPassword());

            try{
                currentUser.login(token);
            }catch (AuthenticationException e){
                System.out.println("认证失败");

                return "login";
            }
        }
        //登录成功后查询此登录用户能看到的菜单集合
        SysUser user = (SysUser) currentUser.getPrincipal();
        List<SysMenu> sysMenuList = sysUserService.getMenuListByUserId(user.getUserId());
        map.put("menuList",sysMenuList);
        //登录认证回到首页
        return "index";
    }

    @RequestMapping("/selectByCondition")
    public String selectByCondition(Page page, SysUser sysUser, ModelMap map){
        //得到一个pageInfo对象
        PageInfo<SysUser> pageInfo = sysUserService.selectByCondition(page,sysUser);
        //装起来
        map.put("pageInfo",pageInfo);
        map.put("sysUser",sysUser);
        map.put("url","sysUser/selectByCondition");

        Gson gson = new Gson();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userName",sysUser.getUserName());
        paramMap.put("flag",sysUser.getFlag());
        map.put("params",gson.toJson(paramMap));
        return "user/user_list";

    }

    /**
     *跳转到用户的添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/user_add";
    }

    /**
     * 添加用户
     */
    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysUser sysUser){
        SysResult sysResult = new SysResult();
        int count = sysUserService.insertSelective(sysUser);
        if (count > 0) {
            sysResult.setResult(true);
            sysResult.setData("添加成功!");
        } else {
            sysResult.setResult(false);
            sysResult.setData("添加失败!");
        }
        return sysResult;
    }

    /**
     * 修改，数据回显
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Long userId,ModelMap map){
        SysUser sysUser = sysUserService.selectByPrimaryKey(userId);
        map.put("sysUser",sysUser);
        return "user/user_update";
    }
}
