package com.auth.shiro;

import com.auth.threads.ThreadLocalToken;
import com.auth.utils.JwtUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
@Scope("prototype")
public class OAuth2Filter extends AuthenticatingFilter {
    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Autowired
    private JwtUtil jwtUtil;
//    @Autowired
//    private RedisTemplate redisTemplate;

    private String verifedToken;

    /**
     * 拦截请求之后，用于把令牌字符串封装成令牌对象
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request,
                                              ServletResponse response) throws Exception {
        return new OAuth2Token(verifedToken);
    }

    /**
     * 拦截请求，判断请求是否需要被Shiro处理
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        // Ajax提交application/json数据的时候，会先发出Options请求
        // 这里要放行Options请求，不需要Shiro处理
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        // 除了Options请求之外，所有请求都要被Shiro处理
        return false;
    }

    /**
     * 该方法用于处理所有应该被Shiro处理的请求
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        //允许跨域请求
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));

        threadLocalToken.clear();
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request, "token");
        String refreshToken = getRequestToken((HttpServletRequest) request, "refreshToken");
        if (StringUtils.isBlank(token) && StringUtils.isBlank(refreshToken)) {
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }
        HashMap<String,String>tokenMap = new HashMap<String,String>();
        tokenMap.put("token", token);
        tokenMap.put("refreshToken", refreshToken);
        try {
            jwtUtil.verifierToken(token); //检查令牌是否本系统签发&&是否过期
            threadLocalToken.setToken(tokenMap);
        } catch (TokenExpiredException e) {
            try {
                jwtUtil.verifierToken(refreshToken); //检查刷新令牌是否本系统签发&&是否过期
                int userId = jwtUtil.getUserId(refreshToken);
                token = jwtUtil.createToken(userId);  //生成新的令牌
                refreshToken = jwtUtil.createRefreshToken(userId);
                //给response添加token也可以用HttpServletRequestWrapper 实现，这里使用线程变量和aop切面实现
                tokenMap.put("token", token);
                tokenMap.put("refreshToken", refreshToken);
                threadLocalToken.setToken(tokenMap);
            } catch (TokenExpiredException e1){
                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                resp.getWriter().print("令牌已经过期");
                return false;
            } catch (JWTDecodeException e1) {
                // 非本系统签发
                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                resp.getWriter().print("无效的令牌");
                return false;
            }
        } catch (JWTDecodeException e) {
            // 非本系统签发
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }
        this.verifedToken = token;
        boolean bool = executeLogin(request, response);
        return bool;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request, ServletResponse response) {
        //login失败（认证授权失败）
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
        resp.setContentType("application/json;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        try {
            resp.getWriter().print(e.getMessage());
        } catch (IOException exception) {

        }
        return false;
    }

    /**
     * 获取请求头里面的token
     */
    private String getRequestToken(HttpServletRequest httpRequest, String tokenName) {
        //从header中获取token
        String token = httpRequest.getHeader(tokenName);

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter(tokenName);
        }
        return token;

    }

    @Override
    public void doFilterInternal(ServletRequest request,
                                 ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //业务调用前处理
        super.doFilterInternal(request, response, chain);
        //业务调用后处理
    }
}


