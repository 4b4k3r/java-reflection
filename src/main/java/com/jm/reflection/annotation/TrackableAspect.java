package com.jm.reflection.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class TrackableAspect
{
    @Around("@annotation(Trackable)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable
    {
        String requestedMethod = joinPoint.getSignature().getName();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        long startedAt = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        System.out.printf("New request: Method [%s], Source : [%s], Elapsed ime [%s]ms Response [%s]\n",
                requestedMethod, servletRequest.getRemoteAddr(), (System.currentTimeMillis() - startedAt), result);
        return result;
    }
}
