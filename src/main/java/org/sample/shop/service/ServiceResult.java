package org.sample.shop.service;

import org.sample.shop.enums.business.BusinessCode;

/**
 * Service层总是返回一个这样的对象，一般总是需要某种开放接口层
 * （HTTP接口或者其它）对其进行封装，它本人并不不知道要被谁封装
 * （降低耦合），仅负责封装基本的服务执行结果信息。
 * 为了把它设计得尽可能简单、通用、好懂,基本上是仿照成熟的RESTful
 * 接口进行设计，即返回状态码（比HTTP状态码详细得多，接口调用方可
 * 以参考公共约定进行渲染呈现）+ 消息体/数据（具体描述由开发接口层
 * 来添加）。
 * 因为采用泛型，因此只需要阅读接口就可以知道包含怎样的数据。
 * 另外，由于把Service层作为调用终点站，所以要在Service层处理
 * 所有的异常并转化为业务表达。
 */
public class ServiceResult<T> {

    private final BusinessCode code; // 类比HTTP状态码，但是描述的是业务执行结果

    private T data = null; // 类比HTTP消息体

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

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}