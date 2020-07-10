package com.shadow.common.bean;


import lombok.Getter;


/**
 * @author cuipeng 2020/7/8 15:09
 */
public class ResultDTO<T> {

    @Getter
    private Integer code;
    @Getter
    private String message;
    @Getter
    private T data;
    @Getter
    private Integer total;

    public ResultDTO() {
    }

    /**
     * 操作成功
     */
    public static <T> ResultDTO<T> success() {
        ResultDTO dto = new ResultDTO();
        dto.setCode(200);
        dto.setMessage("操作成功");
        return dto;
    }

    /**
     * 服务超时
     */
    public static <T> ResultDTO<T> timeout() {
        ResultDTO dto = new ResultDTO();
        dto.setCode(500);
        dto.setMessage("服务超时");
        return dto;
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

    public ResultDTO<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return "{code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}