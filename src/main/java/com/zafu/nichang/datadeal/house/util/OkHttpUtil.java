package com.zafu.nichang.datadeal.house.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


public class OkHttpUtil {

    /**
     * 获取html
     * @param url
     * @param cookie
     * @return
     * @throws Exception
     */
    public static String getHtmlByOkHttp(String url,String cookie) throws  Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cookie", cookie)
                .addHeader("referer", "http://www.dianping.com/shop")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "1d550453-0cc9-b8a8-9933-26bc84190062")
                .build();
        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            return response.body().string();
        } else {
            return null;
        }
    }
}
