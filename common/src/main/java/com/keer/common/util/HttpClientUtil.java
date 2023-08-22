package com.keer.common.util;


import com.keer.common.entity.HttpResponseData;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;


public class HttpClientUtil {
    private static final int CONNECT_TIME_OUT = 35000;
    private static final int REQUEST_TIME_OUT = 35000;
    private static final int SOCKET_TIME_OUT = 60000;

    private static RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)// 连接主机服务超时时间
                .setConnectionRequestTimeout(REQUEST_TIME_OUT)// 请求超时时间
                .setSocketTimeout(SOCKET_TIME_OUT)// 数据读取超时时间
                .build();
        return requestConfig;
    }

    public static HttpResponseData getRequest(String url, Map<String, String> params, Map<String, String> headers) {
        CloseableHttpClient client = HttpClients.createDefault();
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (uriBuilder == null) {
            return null;
        }

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet())
                uriBuilder.addParameter(key, params.get(key));
        }
        //创建httpGet远程连接实例,这里传入目标的网络地址
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (httpGet == null) {
            return null;
        }


        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                httpGet.setHeader(key, headers.get(key));
            }
        }

        // 为httpGet实例设置配置
        httpGet.setConfig(getRequestConfig());
        //执行请求
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
            return null;
        }
        return getResponse(response);
    }

    public static HttpResponseData postForJson(String url, Map<String, String> headers, String jsonStr) {
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost post = new HttpPost(url);
        post.setConfig(getRequestConfig());
        HttpEntity entity = new StringEntity(jsonStr, "UTF-8");
        post.setEntity(entity);
        post.setHeader("Content-type", "application/json");
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                post.addHeader(key, headers.get(key));
            }
        }
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
            return null;
        }
        return getResponse(response);
    }

    private static HttpResponseData getResponse(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        HttpResponseData data = new HttpResponseData();
        data.setStatusCode(statusCode);
        HttpEntity entity = response.getEntity();
        //通过EntityUtils中的toString方法将结果转换为字符串
        String str = null;
        try {
            str = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.setEntity(str);
        return data;
    }
}
