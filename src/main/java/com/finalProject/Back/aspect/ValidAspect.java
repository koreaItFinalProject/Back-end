package com.finalProject.Back.aspect;

import org.apache.tomcat.jni.Proc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

public class ValidAspect {

    @Pointcut("@annotation(com.finalProject.Back.aspect.annotation.ValidAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        BindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }

        return proceedingJoinPoint.proceed();
    }
}
