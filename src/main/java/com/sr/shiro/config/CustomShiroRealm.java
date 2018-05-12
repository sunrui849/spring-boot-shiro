package com.sr.shiro.config;

import com.sr.shiro.bean.User;
import com.sr.shiro.bean.UserRole;
import com.sr.shiro.service.QueryService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/12.
 */
public class CustomShiroRealm extends AuthorizingRealm {

    @Autowired
    private QueryService queryService;

    /**
     * 如果访问带有权限的页面，走该方法，给用户添加权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("------ 权限 >>>>>>"+principalCollection.getRealmNames());
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<UserRole> users = queryService.findRoleByUsername(user.getNickName());
        Set<String> perms = new HashSet<>();
        for (UserRole role:users) {
            perms.add(role.getRoleName());
        }
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        if (!CollectionUtils.isEmpty(perms)) {
            // 权限加入AuthorizationInfo认证对象
            auth.setRoles(perms);
            //auth.setStringPermissions(perms);
        }
        return auth;
    }

    /**
     * 重写获取用户信息的方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = queryService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPswd(), //密码
                //ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt    /*不采用加盐算法*/
                getName()  //realm name
        );

        return authenticationInfo;
    }
}
