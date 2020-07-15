package com.shadow.common.exception;

import lombok.Data;

/**
 * 自定义异常
 * @author cuipeng 2020/7/14 15:22
 */
@Data
public class ShadowException extends RuntimeException {

    /**
     * 状态码
     */
    protected Integer state;

    /**
     * 描述信息
     */
    protected String message;

    /**
     * 原异常
     */
    protected Exception srcException;


    public ShadowException() {
        this.state = ShadowStatus.ERROR_BUSINESS.getState();
        this.message = ShadowStatus.ERROR_BUSINESS.getMessage();
    }

    public ShadowException(ShadowStatus shadowStatus) {
        this.state = shadowStatus.getState();
        this.message = shadowStatus.getMessage();
    }

    public ShadowException(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public ShadowException(ShadowStatus shadowStatus, Exception srcException) {
        this.state = shadowStatus.getState();
        this.message = shadowStatus.getMessage();
        this.srcException = srcException;
    }

    public ShadowException(Integer state, String message, Exception srcException) {
        this.state = state;
        this.message = message;
        this.srcException = srcException;
    }

}
