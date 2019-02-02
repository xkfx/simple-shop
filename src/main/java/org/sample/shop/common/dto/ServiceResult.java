package org.sample.shop.common.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.sample.shop.common.enums.business.BusinessCode;

public class ServiceResult<T> {

    private static final String SERVER_ERROR = "服务器内部错误！";

    private boolean success;

    private T data = null;

    private String error = "success";

    public ServiceResult(T data) {
        this.data = data;
        this.success = true;
    }

    public ServiceResult(String error) {
        this.error = error;
        this.success = false;
    }

    public static <T> ServiceResult<T> ok(T data) {
        return new ServiceResult<T>(data);
    }

    public static <T> ServiceResult<T> fail(String error) {
        return new ServiceResult<T>(error);
    }

    public static <T> ServiceResult<T> error() {
        return new ServiceResult<T>(SERVER_ERROR);
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }

    private BusinessCode code; // 类比HTTP状态码，但是描述的是业务执行结果

    public ServiceResult(BusinessCode code, T data) {
        this.data = data;
        this.code = code;
    }

    public ServiceResult(BusinessCode code) {
        this.code = code;
    }

    public int getCode() {
        return code.getCode();
    }

    public String getDefaultDescription() {
        return code.getDefaultDescription();
    }
}