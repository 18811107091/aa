package com.lening.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * 创作时间：2021/4/13 15:06
 * 作者：王波波
 */
public class Interceptor implements HandlerInterceptor {


    private List<String> exceptUrls;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        System.out.println(uri);
        //uri和url有什么区别
        if(exceptUrls.contains(uri)){
            //放行
            return true;
        }else{
            //需要过滤的，首先要判断他有没有登录，要是没有登录回去登录
            Object login = request.getSession().getAttribute("gologin");
            if(login==null){
                //没登录并且不是特殊的
                request.getRequestDispatcher("/index.html").forward(request, response);
            }else{
                //表示登录了，但是访问的是不是自己的url就不知道了，需要处理一下，判断一下
                Set<String> urls = (Set<String>)request.getSession().getAttribute("urls");
                if(urls!=null){
                    if(urls.contains(uri)){
                        return true;
                    }else{
                       return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }
}
