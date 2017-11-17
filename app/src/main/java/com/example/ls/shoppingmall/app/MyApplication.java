package com.example.ls.shoppingmall.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by ls on 2017/11/8.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        *初始化Okhttp
        * */
        context = this;
        initOkhttpClient();
        x.Ext.init(this);
        //相信https证书
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

    }

    public static Context getInstance() {
        return context;
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置呀
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
