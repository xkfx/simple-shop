package org.sample.shop.common.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.sample.shop.common.annotation.Validator;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.exception.ValidateException;
import org.sample.shop.common.util.EasyValidator;
import org.sample.shop.common.util.validators.RegexpValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * 该切面做3件事情：参数校验、异常处理、错误日志
 */
@Aspect
@Configuration
public class ServiceMethodAspect {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

    private static final EasyValidator EASY_VALIDATOR = new EasyValidator();

    static {
        EASY_VALIDATOR.addValidator(new RegexpValidator());
    }

    @Pointcut("execution(* org.sample.shop.common.service.impl.*.*(..))")
    public void serviceMethod() {}

    @Around("serviceMethod()")
    public ServiceResult work(ProceedingJoinPoint jp) {
        // 参数校验
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Parameter[] parameter = method.getParameters(); // 便于获取注解对象
        String[] parameterName = DISCOVERER.getParameterNames(method);
        Object[] parameterValue = jp.getArgs();
        String currentValidatorName;
        try {
            // 逐个参数进行校验，先尝试获取Validator注解值，注解不存在用参数名作为校验器名
            for (int i = 0; i != parameter.length; ++i) {
                if (parameter[i].getAnnotation(Validator.class) != null) {
                    currentValidatorName = parameter[i].getAnnotation(Validator.class).value();
                } else {
                    currentValidatorName = parameterName[i];
                }
                LOGGER.info("check: object={}, validatorName={}", parameterValue[i], currentValidatorName); // 偶尔出现校验异常有日志可查 TODO 用户信息
                EASY_VALIDATOR.check(parameterValue[i], currentValidatorName);
            }
            return (ServiceResult) jp.proceed();
        } catch (ValidateException e) {
            return ServiceResult.fail(e.getMessage());
        } catch (Throwable throwable) {
            // 错误日志
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
