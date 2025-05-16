package com.jinu.bitool.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // ✅ Controller, Service, Repository 계층 전체에 공통 적용
    @Around("execution(* com.jinu.bitool.controller..*(..)) || " +
            "execution(* com.jinu.bitool.service..*(..)) || " +
            "execution(* com.jinu.bitool.repository..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("▶️ 진입: {}", methodName);
        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            log.info("✅ 완료: {} (소요시간: {}ms)", methodName, duration);
            return result;
        } catch (Throwable e) {
            log.error("❌ 예외 발생: {} - {}", methodName, e.getMessage(), e);
            throw e;
        }
    }
}
