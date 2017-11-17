package com.example.ls.shoppingmall.utils.httputils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by ls on 2017/11/10.
 */
//代理类用来将网络交互层和view层分开
public class HttpHelper implements IHttpProcessor {
    //代理层要持有代理接口 引用
    private static IHttpProcessor mIHttpPrecessor;
    //代理类初始化让代理类去获取数据
    private static HttpHelper _instance;

    public static HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (_instance == null) {
                _instance = new HttpHelper();
            }
        }
        return _instance;
    }

    public static void init(IHttpProcessor iHttpProcessor) {
        mIHttpPrecessor = iHttpProcessor;
    }

    @Override
    public void get(String url, Map<String, Object> parames,ICallback callback) {
        //这里拼接字符串：好像我们这里要全部用post可以不写的。谁知道了。一会儿要解决https好烦
        final String finalUrl = appendParamers(url, parames);
        mIHttpPrecessor.get(finalUrl, parames, callback);
    }

    @Override
    public void post(String url, Map<String, Object> parames, ICallback callback) {
        //post也可以以拼接的方式传递到服务器。
        mIHttpPrecessor.post(url, parames, callback);
    }

    static String appendParamers(String url, Map<String, Object> paramers) {
        //1.获取最终URL方法一：这种累加的方式拼接新能很低的。所以用StringBuider
        /* String url_result;
        url_result = url;
        Set<String> keyset = paramers.keySet();
        Object[] keyArray = keyset.toArray();
        for (int i = 0; i < keyArray.length; i++) {
            url_result += "&" + keyArray[i].toString() + "=" + paramers.get(keyArray[i].toString());
        }
        return url_result;*/
        /**
         * 2:方法二：
         StringBuffer stringBuffer = new StringBuffer();
         for (HashMap.Entry<String, Object> enty : paramers.entrySet()) {
         String strin = "&" + enty.getKey() + "=" + enty.getValue();
         stringBuffer.append(strin);
         }
         return url + stringBuffer.toString();**/

        if (paramers == null && paramers.isEmpty()) {
            return url;
        }
        StringBuilder urlBuider = new StringBuilder(url);

        if (urlBuider.indexOf("?") <= 0) {
            urlBuider.append("?");
        } else {
            if (!urlBuider.toString().endsWith("?")) {
                urlBuider.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : paramers.entrySet()) {
            urlBuider.append(entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        return urlBuider.toString();
    }

    //中文之类的最好编码一下呗。麻痹烦死了
    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
