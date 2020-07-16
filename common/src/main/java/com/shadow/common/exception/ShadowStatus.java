package com.shadow.common.exception;

/**
 * 状态枚举
 * @author cuipeng 2020/7/14 15:57
 */
public enum  ShadowStatus {

    /**
     * 20000
     * <p>操作成功</p>
     */
    SUCCESS(20000, "操作成功"),

    /**
     * 30000
     * <p>校验异常</p>
     */
    ERROR_VALIDATED(30000, "校验异常"),

    /**
     * 40000
     * <p>业务异常</p>
     */
    ERROR_BUSINESS(40000, "业务异常"),

    /**
     * 50000
     * <p>服务超时</p>
     */
    ERROR_NETWORK(50000, "服务超时");


    /**
     * 状态码
     */
    private final Integer state;

    /**
     * 描述信息
     */
    private final String message;


    ShadowStatus(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public Integer getState() {
        return this.state;
    }

    public String getMessage() {
        return this.message;
    }

}
