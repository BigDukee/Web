package com.xxxx.entity.valueObject;

/**
 * 消息模型对象（数据响应）
 *  状态码
 *      1=成功，0=失败
 *  提示信息
 *      字符串
 *  回显数据
 *      object对象
 * @author BigDuke
 */
public class MessageModel {
    /*状态码*/
    private Integer code = 1;
    //提示信息
    private String msg = "成功！";
    /*回显对象（基本数据类型，字符串类型，List，Map等）*/
    private Object object;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
