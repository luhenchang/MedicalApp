package com.example.ls.shoppingmall.utils.httputils;

/**
 * Created by ls on 2017/11/10.
 */

public class JSONBens {

    /**
     * msg : 天呢！用户已注册
     * code : 1
     * data : {}
     */

    private String msg;
    private String code;
    private String data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JSONBens{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
