package com.zafu.nichang.datadeal.hotel.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkHttpUtil {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    /**
     * 获取html
     * @param url
     * @param cookie
     * @return
     * @throws Exception
     */
    public static String getHtmlByOkHttp(String url,String cookie) throws  Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cookie", cookie)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "0852c936-5062-a3da-7472-94340ce9eca4")
                .build();
        Response response = client.newCall(request).execute();
        if(response.code()==200){
            return response.body().string();
        }else{
            return null;
        }
    }


    /**
     *  获取html 飞猪
     * @param url
     * @param cookie
     * @return
     * @throws Exception
     */
    public static String getHtmlByOkHttp2(String url,String cookie) throws  Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cookie", cookie)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "0852c936-5062-a3da-7472-94340ce9eca4")
                .build();
        Response response = client.newCall(request).execute();
        if(response.code()==200){
            return response.body().string();
        }else{
            return null;
        }
    }
    /**
     *  获取html 美团
     * @param url
     * @param cookie
     * @return
     * @throws Exception
     */
    public static String getHtmlByOkHttp3(String url,String cookie) throws  Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cookie", cookie)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "0852c936-5062-a3da-7472-94340ce9eca4")
                .build();
        Response response = client.newCall(request).execute();
        if(response.code()==200){
            return response.body().string();
        }else{
            return null;
        }
    }
    public static String getHtmlByOkHttp4(String url,String cookie) throws  Exception{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cookie", cookie)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "1d550453-0cc9-b8a8-9933-26bc84190062")
                .build();
        Response response = client.newCall(request).execute();
        if(response.code()==200){
            return response.body().string();
        }else{
            return null;
        }
    }
}
