package com.movie.test;

import com.ruoyi.common.core.domain.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class MyValidAspect {
    @Pointcut("execution(public * com.movie.controller..*(..))")
    public void pointCut(){ }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //代理类的method对象
        Method method = signature.getMethod();
        // 这个方法才是目标对象上有注解的方法
        // 获取目标方法的方式
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());

        //该方法只能获取方法上的注解
        //MyValid myValid = method.getAnnotation(MyValid.class);
        //Annotation[] myValids = method.getDeclaredAnnotations();

        //该方法只能获取参数上的注解
        //method.getParameterAnnotations(); 目前看来method也可以获取到参数上的注解
        Annotation[][] parameterAnnotations = realMethod.getParameterAnnotations();
        int index = 0;
        //标号  break continue可用，用于控制循环跳出的位置，比如当前A在两个for循环的外面，则跳出两个for循环
        A:
        for (Annotation[] annotations : parameterAnnotations) {
            for (Annotation annotation : annotations) {
                //获取注解名
                if (annotation instanceof MyValid){
                    MyValid myValid = (MyValid) annotation;
                    //注解设置的值
                    int value = myValid.value();
                    String message = myValid.message();
                    //请求参数
                    int id = (Integer) args[0];
                    if (id < value) {
                        System.out.println("第" + index + "个参数验证失败，输入值为：" + id);
                        throw new RuntimeException(message);
                    }
                    break A;
                }
            }
            index ++;
        }
        // 执行具体的方法，在使用 Around 类的时候，需要对执行方法进行调用，否则只会执行 Around 方法 normalPointBefore 里面的逻辑
        return joinPoint.proceed(args);
    }
}
