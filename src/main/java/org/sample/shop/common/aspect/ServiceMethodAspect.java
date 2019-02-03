package org.sample.shop.common.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sample.shop.common.dto.ServiceResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.util.Arrays;

/**
 * 该切面做3件事情：参数校验、异常处理、错误日志
 */
@Aspect
@Configuration
public class ServiceMethodAspect {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();


    @Pointcut("execution(* org.sample.shop.common.service.impl.*.*(..))")
    public void serviceMethod() {}

    @Around("serviceMethod()")
    public ServiceResult work(ProceedingJoinPoint jp) {

        try {
            return (ServiceResult) jp.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(new DetailedInfo(jp.getArgs(), throwable.getMessage()), throwable);
            return ServiceResult.error();
        }
    }

    private class DetailedInfo {

        private Object[] args;

        private String message;

        DetailedInfo(Object[] args, String message) {
            this.args = args;
            this.message = message;
        }

        @Override
        public String toString() {
            return "DetailedInfo{" +
                    "args=" + Arrays.toString(args) +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
