package com.meng.utils;

import com.meng.bean.dto.ResultData;

public class ResultUtil {

    /**
     * 返回成功
     *
     * @return 结果
     */
    public static ResultData success() {
        return success("请求成功");
    }

    /**
     * 返回成功
     *
     * @param message 消息
     * @return 结果
     */
    public static ResultData success(String message) {
        return success(message, null);
    }

    /**
     * 返回成功
     *
     * @param data 消息
     * @return 结果
     */
    public static ResultData success(Object data) {
        return success("请求成功", data);
    }

    /**
     * 返回成功
     *
     * @param message 消息
     * @param data    数据
     * @return 结果
     */
    public static ResultData success(String message, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setMsg(message);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 返回异常
     *
     * @return 结果
     */
    public static ResultData error() {
        return error(500, "请求异常", null);
    }

    /**
     * 返回异常
     *
     * @param message 消息
     * @return 结果
     */
    public static ResultData error(String message) {
        return error(500, message, null);
    }

    /**
     * 返回异常
     *
     * @param message 消息
     * @return 结果
     */
    public static ResultData error(String message, Object data) {
        return error(500, message, data);
    }

    /**
     * 返回异常
     *
     * @param message 消息
     * @return 结果
     */
    public static ResultData error(Integer code, String message) {
        return error(code, message, null);
    }

    /**
     * 返回异常
     *
     * @param code    返回code
     * @param message 消息
     * @return 结果
     */
    public static ResultData error(Integer code, String message, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(message);
        resultData.setData(data);
        return resultData;
    }

}
