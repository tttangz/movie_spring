package com.auth.aop;

import com.auth.threads.ThreadLocalToken;
import com.ruoyi.common.core.domain.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
public class TokenAspect {
    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Pointcut("execution(public * com.auth.controller.UserController.*(..))")
    public void aspect(){

    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        R r=(R)point.proceed();
        HashMap data = (HashMap)r.getData();
        HashMap<String, String > tokenMap=threadLocalToken.getTokenMap();
        if(tokenMap!=null){
            data.putAll(tokenMap);
            threadLocalToken.clear();
        }
        return r;
    }
}