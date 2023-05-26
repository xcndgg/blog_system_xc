package com.xc.blog_system_xc.controller.scheduletask;

import com.xc.blog_system_xc.utils.AdminCommons;
import com.xc.blog_system_xc.utils.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的Interceptor拦截器类，用于封装请求后的数据类到request域中，供html页面使用
 * 注意：自定义Mvc的Interceptor拦截器类
 *  1、使用@Configuration注解声明
 *  2、自定义注册类将自定义的Interceptor拦截器类进行注册使用
 */
@Configuration
public class BaseInterceptor implements HandlerInterceptor {
    @Autowired
    private Commons commons;
    @Resource
    private AdminCommons adminCommons;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 用户将封装的Commons工具返回页面
        request.setAttribute("commons",commons);
        request.setAttribute("adminCommons", adminCommons);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

