package com.cl.oa.realm;

import com.cl.oa.entity.SysUser;
import com.cl.oa.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private ISysUserService sysUserService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证处理");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //得到用户名
        String username = usernamePasswordToken.getUsername();
        //通过用户名查询用户对象

        SysUser sysUser = sysUserService.getUserByName(username);
        if (sysUser!=null){
            //加盐值
            ByteSource byteSource = ByteSource.Util.bytes(username);
            return  new SimpleAuthenticationInfo(sysUser,sysUser.getUserPassword(),byteSource,this.getName());
        }
        return null;
    }
}
