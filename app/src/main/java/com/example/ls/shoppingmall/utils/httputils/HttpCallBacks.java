package com.example.ls.shoppingmall.utils.httputils;

import android.util.Log;

import com.solidfire.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ls on 2017/11/10.
 */
//我想定义一个类用来直接这里解析完获取数据多好。
public abstract class HttpCallBacks<Results> implements ICallback {
    @Override
    public void onSuccess(String success_str) {
        Log.e("result",success_str);

        //万能解析方案：
        Gson gson = new Gson();
        //获取类信息通过反射来动态获取万能的解决
        Type type = analysisClassInfo(this);

        Results objResult = gson.fromJson(success_str, type);

        onSuccess(objResult);
    }

    @Override
    public void onFailure(String fail_str) {
        onFailures(fail_str);
    }
    //这里activity里面直接获取到类多完美
    public abstract void onSuccess(Results result);

    public abstract void onFailures(String fail_str);

    public static Type analysisClassInfo(Object obj) {
        //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type types = obj.getClass().getGenericSuperclass();
        //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        Type[] params = ((ParameterizedType) types).getActualTypeArguments();
        return params[0];
    }

}
