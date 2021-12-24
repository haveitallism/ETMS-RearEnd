package com.group8.config;

import com.group8.entity.JsonWebToken;
import com.group8.utils.JWTUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class MyRealm extends AuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JsonWebToken;
    }

    /**
     * 注入service
     */
    /*@Autowired
    LoginService loginService;
    @Autowired(required = false)
    RbacRoleService rbacRoleService;
    @Autowired
    RbacPermService rbacPermService;*/
    @Autowired(required = false)
    RedisTemplate redisTemplate;

    /**
     * 授权 逻辑代码
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       /* String jwt = principals.getPrimaryPrincipal()+"";
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        RbacManager rbacManager = new RbacManager();
        String userName = JWTUtils.getUserName(jwt);
        rbacManager.setAccount(userName);
        rbacRoleService.findAll(rbacManager);
        simpleAuthorizationInfo.addRole("admin");*/

        return null;
    }

    /**
     * 认证 逻辑代码
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //基于JsonWebToken认证
        String jwt = ((JsonWebToken) token).getToken();
        String username = JWTUtils.getUserName(jwt);
        //与redis数据库中的token对比
        String redisToken = (String)redisTemplate.opsForValue().get(username);
        if (!jwt.equals(redisToken)) {
            throw new AuthenticationException("认证失败！请重新登录");
        }else {
            return new SimpleAuthenticationInfo(jwt, jwt, "MyRealm");
        }
    }
}
