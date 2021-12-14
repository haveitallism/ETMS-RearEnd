package com.group8.config;

import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro核心配置类  完成SecurityManager的配置
 */
@Configuration
public class ShiroConfig {

    /**
     * 禁用session  不保存登录状态  保证每次都需要认证
     * @return
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false); //禁用session
        return sessionStorageEvaluator;
    }

    /**
     * 配置安全管理器securityManager  注入自定义域MyRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm){
        //创建自定义的安全管理器
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //放入自定义域
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 配置过滤器
     * @param defaultSecurityManager
     * @return
     */
    @Bean("shiroFilterFactoryBean") // 必须要用这个名字
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultSecurityManager){
        //创建shiro的过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置属性 安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("shiroFilter", new ShiroFilter());
        //将定义的filter集合放入过滤器中
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        //设置对应路径是否需要过滤访问  anon代表可以匿名访问（不登陆也可以访问）
        filterChainDefinitionMap.put("/**", "shiroFilter");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/401", "anon");
        //设置过滤器功能
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 开始shiro的注解支持  使用注解需要配置的
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 管理subject主体的生命周期组件
     * @return
     */
    @Bean("lifecycleBeanPostProcessor") // 必须要用这个名字
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
