package com.example.ls.shoppingmall.utils.httputils;

/**
 * Created by ls on 2017/11/10.
 */
//这个接口就是获取到值的接口
public interface ICallback {
    //成功
    void onSuccess(String success_str);
    //失败
    void onFailure(String fail_str);
}
