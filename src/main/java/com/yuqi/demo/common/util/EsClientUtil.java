package com.yuqi.demo.common.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * es帮助类
 *
 * @author yuqi
 * @date 2021-01-31 22:02
 */
public class EsClientUtil {

    public static RestHighLevelClient getClient() {
        // 创建httpHost对象
        HttpHost httpHost = new HttpHost("localhost", 9200);
        // 创建RestClientBuilder
        RestClientBuilder clientBuilder = RestClient.builder(httpHost);
        //创建restHighLevelClient
        RestHighLevelClient client = new RestHighLevelClient(clientBuilder);
        return client;
    }
}
