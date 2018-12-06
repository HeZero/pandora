package com.hsp.pandora.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * @author heshipeng
 * http 调用工具类(httpclient)
 */
public class HttpUtil
{
    public enum ContentType
    {
        XML("text/xml"),
        JSON("application/json"),
        FORMDATA("application/x-www-form-urlencoded");

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        ContentType(String value) {
            this.value = value;
        }
    }

    /**
     * 支持http, https的post调用
     * @param data
     * @return
     * @throws Exception
     */
    public static String post(String url, String data, ContentType contentType) throws Exception {

        HttpClient httpClient = httpClientBuilder();

        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", contentType.getValue());
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");

    }

    /**
     * 支持http, https的get调用
     * @return
     * @throws Exception
     */
    public static String get(String url, ContentType contentType) throws Exception {

        HttpClient httpClient = httpClientBuilder();

        HttpGet httpGet = new HttpGet(url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
        httpGet.setConfig(requestConfig);

        httpGet.addHeader("Content-Type", contentType.getValue());

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");

    }

    /**
     * 构建支持https 的 httpclient
     * @return
     */
    private static HttpClient httpClientBuilder()
    {
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );

        return HttpClientBuilder.create().setConnectionManager(connManager).build();
    }
}
