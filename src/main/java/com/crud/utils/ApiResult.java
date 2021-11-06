package com.crud.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by tommy on 2019/12/22.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class ApiResult<T> {
    public final static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MSG = "处理成功";

    private String code;
    private String msg;
    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FULL_DATE_FORMAT));
    private T data;


    /**
     * 1、内部使用，用于构造成功的结果
     * 2、对接外部系统错误码及消息
     *
     * @param code
     * @param msg
     * @param data
     */
    public ApiResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return ApiResult
     */
    public static ApiResult success(Object data) {
        return new ApiResult<>(SUCCESSFUL_CODE, SUCCESSFUL_MSG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return ApiResult
     */
    public static ApiResult success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return ApiResult
     */
    public static ApiResult fail() {
        return new ApiResult("-1","fail",null);
    }

    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
