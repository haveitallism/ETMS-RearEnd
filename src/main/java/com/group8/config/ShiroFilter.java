package com.group8.config;

import com.group8.entity.JsonWebToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器  继承  BasicHttpAuthenticationFilter
 * 不需要注解配置  通过配置类的形式注册到安全管理器中
 */
public class ShiroFilter extends BasicHttpAuthenticationFilter {

    /**
     * 进行全局跨域处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        //跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        //放行，直接进去controller
        return super.preHandle(request, response);
    }

    /**
     * 尝试登陆
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //确保一点，判断请求头中有无数据
        if (isLoginAttempt(request,response)) {
            try {
                //如果有数据则尝试登录
                executeLogin(request,response);
            } catch (Exception e) {
                e.printStackTrace();
                response401(request,response);
            }
        }else{
            //s登录尝试失败(账号密码或者token错误)
            //返回401信息 没有登录
            response401(request,response);
        }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest request1 = (HttpServletRequest) request;
        /*//第1套方法：从请求头中获取，userdata，对象 有这个对象则表示可以认证
        String userdata = request1.getHeader("userdata");
        return userdata != null;*/

        //第2套方法：从请求头中获取，userdata，对象 有这个对象则表示可以认证
        String token = request1.getHeader("Authorization");
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest request1 = (HttpServletRequest) request;
        //第2套方法：从请求头中获取，token，对象 有这个对象则表示可以认证
        String token = request1.getHeader("Authorization");
        Subject subject = SecurityUtils.getSubject();
        subject.login(new JsonWebToken(token));
        return true;
    }



    public void response401(ServletRequest request, ServletResponse response){
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        //请求401接口
        try {
            request1.getRequestDispatcher("/401").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
