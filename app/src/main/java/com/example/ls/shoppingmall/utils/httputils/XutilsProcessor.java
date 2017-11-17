package com.example.ls.shoppingmall.utils.httputils;

import android.content.Context;
import android.util.Log;

import com.example.ls.shoppingmall.app.MyApplication;

import org.json.JSONObject;
import org.xutils.BuildConfig;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by ls on 2017/11/10.
 */
//这个类里面我们处理我们的网络请求
public class XutilsProcessor implements IHttpProcessor {
    @Override
    public void get(String url, Map<String, Object> parames, final ICallback callback) {
        RequestParams requestParams = new RequestParams(url);

        /*SSLContext sslContext = getSSLContext(MyApplication.getInstance());
        if (null == sslContext) {
            if (BuildConfig.DEBUG) Log.e("erro=null", "Error:Can't Get SSLContext!");
            return;
        }

        requestParams.setSslSocketFactory(sslContext.getSocketFactory());*/

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result",result);

                //请求成功返回result
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //请求失败返回失败异常信息
                callback.onFailure(ex.getStackTrace().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> parames, final ICallback callback) {
        JSONObject jsonObject = new JSONObject(parames);
        RequestParams requestParams = new RequestParams(url);

        SSLContext sslContext = getSSLContext(MyApplication.getInstance());
        if (null == sslContext) {
            if (BuildConfig.DEBUG) Log.e("erro=null", "Error:Can't Get SSLContext!");
            return;
        }
        //我们后台需要json传过去
        requestParams.setAsJsonContent(true);
        //以json的方式去传递到后台
        requestParams.setBodyContent(jsonObject.toString());
        requestParams.setSslSocketFactory(sslContext.getSocketFactory());

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //请求成功返回result
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //请求失败返回失败异常信息
                callback.onFailure(ex.getStackTrace().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取Https的证书
     *
     * @param context 上下文
     * @return SSL的上下文对象
     */
    private static SSLContext getSSLContext(Context context) {
        SSLContext mSSLContext;
        CertificateFactory certificateFactory = null;
        InputStream inputStream = null;
        Certificate cer = null;
        KeyStore keystore = null;
        TrustManagerFactory trustManagerFactory = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            inputStream = context.getAssets().open("srca.cer");//这里导入SSL证书文件
            try {
                cer = certificateFactory.generateCertificate(inputStream);
                Log.e("cer", cer.getPublicKey().toString());
            } finally {
                inputStream.close();
            }

            //创建一个证书库，并将证书导入证书库
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, null); //双向验证时使用
            keystore.setCertificateEntry("trust", cer);

            // 实例化信任库
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keystore);

            mSSLContext = SSLContext.getInstance("TLS");
            mSSLContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
/*
            //信任所有证书 （官方不推荐使用）
            mSSLContext.init(null, new TrustManager[]{new X509TrustManager() {

              @Override
              public X509Certificate[] getAcceptedIssuers() {
                  return null;
              }

              @Override
              public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                      throws CertificateException {

              }

              @Override
              public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                      throws CertificateException {

              }
          }}, new SecureRandom());*/
            return mSSLContext;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
