package com.zafu.nichang.datadeal.web.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 解析html类
 *
 * @author 倪畅
 * @version 1.0 2019-01-16
 */
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

}
