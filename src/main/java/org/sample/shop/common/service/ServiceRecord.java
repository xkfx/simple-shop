package org.sample.shop.common.service;

import org.aspectj.lang.reflect.MethodSignature;
import org.sample.shop.common.dto.ServiceResult;

import java.util.Arrays;

/**
 * 方便打印Service层info日志
 */
public class ServiceRecord {

    private long startTime;
    private long endTime;
    private MethodSignature signature;
    private Object[] args;
    private ServiceResult result;

    public ServiceRecord(long startTime, MethodSignature signature, Object[] args) {
        this.startTime = startTime;
        this.args = args;
        this.signature = signature;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("ServiceRecord{")
                .append("signature=").append(signature)
                .append(", args=").append(Arrays.toString(args))
                .append(", result=").append(result)
                .append(", time=").append(endTime - startTime)
                .append("}").toString();
    }
}
