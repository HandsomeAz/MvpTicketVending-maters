package com.geo.mvpframe_maters.network;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * 创建人： created by zlj
 * 时间：2022/03/27 17
 */
public class OkHttp3Utils {
    private static final String TAG = "OkHttp3Utils_log";
    private OkHttpClient mOkHttpClient;
    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {

            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //添加网络连接器
                    //.addNetworkInterceptor(new CookiesInterceptor(MyApplication.getInstance().getApplicationContext()))
                    //设置请求读写的超时时间
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(getLoggingInterceptor())//日志拦截
//                    .addInterceptor(new AppInterceptor())
//                    .cache(cache)//设置缓存
                    .retryOnConnectionFailure(true)//自动重试
                    .build();
        }
        return mOkHttpClient;
    }

    private HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                if (TextUtils.isEmpty(message)) {
                    return;
                }
                String s = message.substring(0, 1);
                //如果收到想响应是json才打印
                if ("{".equals(s) || "[".equals(s)) {
                    LogUtils.json(message);
                } else if (message.contains("http://")) {
                    LogUtils.d(message);
                } else if (message.contains("Exception")) {
                    LogUtils.d(message);
                }
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }


    private static class AppInterceptor implements Interceptor {


        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder();

            // support gzip
            if (originalRequest.body() != null && originalRequest.header("Content-Encoding") != null) {
                builder.header("Content-Encoding", "gzip").method(originalRequest.method(), gzip(originalRequest.body()));
            }

            Response response = chain.proceed(builder.build());

            String json = response.body().string();
            MediaType contentType = response.body().contentType();
            ResponseBody body = ResponseBody.create(contentType, json);
            return response.newBuilder().body(body).build();
        }

        private RequestBody gzip(final RequestBody body) {
            return new RequestBody() {
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public long contentLength() {
                    return -1; // We don't know the compressed length in advance!
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
        }
    }

    /**
     * 设置头信息
     */
    Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .addHeader("Accept-Encoding", "gzip")
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .method(originalRequest.method(), originalRequest.body());
            requestBuilder.addHeader("Authorization", "Bearer ");//添加请求头信息，服务器进行token有效性验证
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };


}
