package com.citi.tts.controller;




import java.io.Serializable;

/**
 * @author ShiWei.Xu
 * @className
 * @description
 * @date create in 10:03 2020/11/17
 **/


public class CommonResponse<T> implements Serializable {
    /**
     服务处理状态: T 成功; F 失败
     */
    private Boolean status;

    /**
     业务状态码: 具体业务具体定义
     */
    private String code;

    /**
     业务状态描述
     */
    private String msg;

    /**
     返回payload
     */
    private T data;

    @Override
    public String toString() {
        String responseBody = "";
        if(null != data){
            responseBody = data.toString();
        }
        if(status == null){
            status = true;
        }
        String statusStr = status ? "T" : "F";
        return statusStr + "|" + code + "|" + msg + "|" + responseBody;
    }


    public CommonResponse(Boolean status, String code, String msg, T data){
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse(){

    }

    public static <T> CommonResponse<T> ok(T data) {
        return ok("SUCCESS","SUCCESS",data);
    }

    public static <T> CommonResponse<T> ok(String code, String msg, T data) {
        CommonResponse<T> resp = new CommonResponse<>();
        resp.setData(data);
        resp.setStatus(true);
        resp.setCode("SUCCESS");
        resp.setMsg(msg);
        return resp;
    }

    public static <T> CommonResponse<T> ok(String msg, T data) {
        return ok("SUCCESS",msg,data);
    }

    public static <T> CommonResponse<T> ok() {
        return ok(null);
    }


    public static <T> CommonResponse<T> fail(String errorCode , String errorMsg) {
        CommonResponse<T> resp = new CommonResponse<>();
        resp.setCode(errorCode);
        resp.setMsg(errorMsg);
        resp.setStatus(true);
        return resp;
    }



    public static <T> CommonResponse<T> error(String errorCode , String errorMsg) {
        CommonResponse<T> resp = new CommonResponse<>();
        resp.setCode(errorCode);
        resp.setMsg(errorMsg);
        resp.setStatus(false);
        return resp;
    }

    /**
     * @Author Tico.Ji
     * @Description 转换data,克隆出新泛型Response
     * @Date 02:33 2020-02-24
     * @Param [resource, data]
     * @return com.zhan.platform.vo.CommonResponse<F>
     */
    public static <T,F> CommonResponse<F> clone(CommonResponse<T> resource, F data){
        CommonResponse<F> response = new CommonResponse<>();
        response.setStatus(resource.getStatus());
        response.setMsg(resource.getMsg());
        response.setCode(resource.getCode());
        response.setData(data);
        return response;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
