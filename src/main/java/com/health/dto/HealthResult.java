package com.health.dto;

// 封装处理结果 给前端调用
public class HealthResult<T> {

    // 请求是否成功
    // 由web层处理
    private boolean success;

    // 封装的数据
    private T data;

    // 错误信息
    private String error;

    public HealthResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public HealthResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

