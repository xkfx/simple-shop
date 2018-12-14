package org.sample.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.sample.shop.enums.ServiceEnum;

/**
 * 成功返回数据，失败返回理由
 */
public class ServiceResult<T> {

    private boolean success;

    @JsonIgnore
    private T data;

    private ServiceEnum message;

    public ServiceResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ServiceResult(boolean success, ServiceEnum message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getDescription() {
        return message.getDescription();
    }

    public int getStatus() {
        return message.getStatus();
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "success=" + success +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}