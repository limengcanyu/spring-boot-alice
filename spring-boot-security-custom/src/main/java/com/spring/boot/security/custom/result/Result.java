package com.spring.boot.security.custom.result;

import java.io.Serializable;

/**
 * <p>Description: 结果对象</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2017/12/6 0006
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 6288374846131788743L;

    /**
     * 返回状态码，默认返回成功
     */
    private Integer statusCode = 0;

    /**
     * 返回信息：成功时为空，错误时才有信息
     */
    private String message = "";

    public Result() {
    }

    public Result(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
