package ru.kuzmina.whiskersshop.aop;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Aspect
@Component
@Data
@RequiredArgsConstructor
public class AppLoggingAspect {

    private HashMap<String, Long> statistic;

    @PostConstruct
    public void init() {
        statistic = new HashMap<>();
    }

    @Around(value = "execution (public * ru.kuzmina.whiskersshop.services..*.*(..))")
    public Object servicesExecutionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long estTime = end - begin;
        statistic.put(((MethodSignature) proceedingJoinPoint.getSignature()).toString(), estTime);
        return out;
    }

}

