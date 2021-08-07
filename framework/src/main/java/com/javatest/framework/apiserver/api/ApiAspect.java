package com.javatest.framework.apiserver.api;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApiAspect extends BasicApiAspect {

    @Autowired
    public ApiAspect(RateLimiterRegistry rateLimiterRegistry) {
        setRateLimiter(rateLimiterRegistry);
    }

    @Pointcut("execution(public * com.javatest.framework.apiserver.api.ApiController.*(..)) ")
    public void apiTopoPointCut() { }


    @Override
    @Around("apiTopoPointCut()")
    public Object apiTopoAroundAdvise(final ProceedingJoinPoint jp) throws Throwable {
        return super.apiTopoAroundAdvise(jp);
    }

}
