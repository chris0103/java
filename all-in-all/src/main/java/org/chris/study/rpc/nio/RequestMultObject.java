package org.chris.study.rpc.nio;

import java.io.Serializable;

public class RequestMultObject implements Serializable {

    private static final long serialVersionUID = 3132836600205356306L;

    // 请求id
    private Long requestId;

    // 服务提供者接口
    private Class<?> clazz;

    // 服务的方法名称
    private String methodName;

    // 参数类型
    private Class<?>[] paramTypes;

    // 参数
    private Object[] args;

    public RequestMultObject(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object[] args) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.args = args;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requstId) {
        this.requestId = requstId;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
