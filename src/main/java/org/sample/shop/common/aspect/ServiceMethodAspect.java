package org.sample.shop.common.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.sample.shop.common.annotation.Validator;
import org.sample.shop.common.pojo.ServiceRecord;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.exception.ValidateException;
import org.sample.shop.common.util.EasyValidator;
import org.sample.shop.common.util.validators.RegexpValidator;
import org.sample.shop.common.util.validators.UserValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 该切面做3件事情：参数校验、异常处理、日志
 */
@Aspect
@Configuration
public class ServiceMethodAspect {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

    private static final EasyValidator EASY_VALIDATOR = new EasyValidator();

    private static final ThreadLocal<ServiceRecord> SERVICE_RECORD_THREAD_LOCAL = new ThreadLocal<>();

    static {
        EASY_VALIDATOR.addValidator(new RegexpValidator());
        EASY_VALIDATOR.addValidator(new UserValidator());
    }

    @Pointcut("execution(* org.sample.shop.common.service.impl.*.*(..))")
    public void serviceMethod() {}

    @Around("serviceMethod()")
    public ServiceResult work(ProceedingJoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Object[] parameterValue = jp.getArgs();
        SERVICE_RECORD_THREAD_LOCAL.set(new ServiceRecord(System.currentTimeMillis(), signature, parameterValue));
        // 参数校验
        Method method = signature.getMethod();
        String[] parameterName = DISCOVERER.getParameterNames(method);
        Parameter[] parameter = method.getParameters(); // 便于获取注解对象
        try {
            String currentValidatorName;
            // 逐个参数进行校验，先尝试获取Validator注解值，注解不存在用参数名作为校验器名
            for (int i = 0; i != parameter.length; ++i) {
                if (parameter[i].getAnnotation(Validator.class) != null) {
                    currentValidatorName = parameter[i].getAnnotation(Validator.class).value();
                } else {
                    currentValidatorName = parameterName[i];
                }
                EASY_VALIDATOR.check(parameterValue[i], currentValidatorName);
            }
            return (ServiceResult) jp.proceed();
        } catch (ValidateException e) {
            return ServiceResult.fail(e.getMessage());
        } catch (Throwable throwable) {
            // 错误日志
            LOGGER.error("", throwable);
            return ServiceResult.error();
        }
    }

    @AfterReturning(pointcut = "serviceMethod()", returning = "result")
    public void logRecord(ServiceResult result) {
        ServiceRecord record = SERVICE_RECORD_THREAD_LOCAL.get();
        if (record != null) {
            record.setResult(result);
            record.setEndTime(System.currentTimeMillis());
            LOGGER.info(record);
        } else {
            LOGGER.warn("Abnormal service method, {}", result);
        }
        SERVICE_RECORD_THREAD_LOCAL.remove();
    }
}
