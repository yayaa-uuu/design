package com.willing.aop.aspectj;

import com.willing.aop.aspectj.annotation.Resubmit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class ResubmitAspect {

    @Around("@annotation(com.willing.aop.aspectj.annotation.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        System.out.println(method.getName());

        //获取注解信息
        Resubmit annotation=method.getAnnotation(Resubmit.class);
        System.out.println(annotation.expire());
        //获取方法请求参数
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //必传参数
        HttpServletRequest request= Objects.requireNonNull(attributes).getRequest();


        //执行锁
        return joinPoint.proceed();
    }


}
