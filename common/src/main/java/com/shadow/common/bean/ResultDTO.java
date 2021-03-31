package com.shadow.common.bean;


import com.shadow.common.exception.ShadowStatus;
import lombok.Getter;


/**
 * @author cuipeng 2020/7/8 15:09
 */
public class ResultDTO<T> {

    /**
     * 状态码
     */
    @Getter
    private Integer code;

    /**
     * 描述信息
     */
    @Getter
    private String message;

    /**
     * 响应数据
     */
    @Getter
    private T data;



    public ResultDTO() {
    }

    public ResultDTO(ShadowStatus shadowStatus) {
        this.code = shadowStatus.getState();
        this.message = shadowStatus.getMessage();
    }

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO(ShadowStatus shadowStatus, T data) {
        this.code = shadowStatus.getState();
        this.message = shadowStatus.getMessage();
        this.data = data;
    }

    public ResultDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 20000
     * <p>操作成功</p>
     */
    public static ResultDTO success() {
        return new ResultDTO(ShadowStatus.SUCCESS);
    }

    /**
     * 40000
     * <p>业务异常</p>
     */
    public static ResultDTO error() {
        return new ResultDTO(ShadowStatus.ERROR_BUSINESS);
    }

    /**
     * 50000
     * <p>服务超时</p>
     */
    public static ResultDTO timeout() {
        return new ResultDTO(ShadowStatus.ERROR_NETWORK);
    }


    public ResultDTO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResultDTO<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultDTO<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "{code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
