package com.common.filter;

import cn.hutool.json.JSONUtil;
import com.common.user.UserInfo;
import com.common.feign.AuthClient;
import com.common.threads.ThreadLocalUser;
import com.ruoyi.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// 生成携带用户信息的线程变量。
// 1.被内部调用模块不应当执行此过滤器，当线程变量设置在本次请求上才有用，通过fegin调用后的线程和本次线程已经不同了
// 2.如果有需要可以user接口可以在注册或登录后直接生成user线程变量，不需要通过fegin，也就是说auth模块不需要执行此过滤器
@WebFilter("/*")
public class UserInfoFilter implements Filter {
    @Autowired
    private ThreadLocalUser threadLocalUser;

    @Autowired
    private AuthClient authClient;

    private static final Logger log = LoggerFactory.getLogger(UserInfoFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    //排除所有内部接口，以及user接口
    private final Set<String> EXCLUDE_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/inner","/user")));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //从header中获取token
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        log.info("Filter : " + path);
        boolean allowed = true;
        for(String excludePath:EXCLUDE_PATHS){
            if (path.contains(excludePath)){
                allowed = false;
            }
        }
        if(allowed){
            String token = req.getHeader("token");
            String refreshToken = req.getHeader("refreshToken");
            if(token==null) {
                token=req.getParameter("token");
            }
            if(refreshToken==null) {
                refreshToken=req.getParameter("refreshToken");
            }
            if(token!=null && refreshToken!=null) {
                R<UserInfo> r = authClient.getUserInfo(token, refreshToken);
                String errorMsg = r.getData().getErrorMsg();
                if(errorMsg==null){
                    threadLocalUser.setUser(r.getData());
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    sendErrorResponse(servletResponse, errorMsg);
                }
            } else {
                String errorMsg = "token 和 refreshToken 不能为空";
                sendErrorResponse(servletResponse, errorMsg);
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void sendErrorResponse(ServletResponse servletResponse, String errorMsg) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("code", HttpStatus.UNAUTHORIZED.value());
        jsonMap.put("message", errorMsg);
        response.getWriter().println(JSONUtil.parse(jsonMap));
    }

    @Override
    public void destroy() {
        threadLocalUser.clear();
        Filter.super.destroy();
    }
}
