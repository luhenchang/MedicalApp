package com.example.ls.shoppingmall.utils.httputils;

import java.util.Map;

/**
 * Created by ls on 2017/11/10.
 */

public interface IHttpProcessor {
    //增删改查 post get
    void get(String url, Map<String, Object> parames, ICallback callback);
    void post(String url, Map<String, Object> parames, ICallback callback);

}
