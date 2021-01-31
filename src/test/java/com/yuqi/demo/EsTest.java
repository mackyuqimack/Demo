package com.yuqi.demo;

import com.yuqi.demo.common.util.EsClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    //同步消息
    @Test
    public void testSyncSend() {
        RestHighLevelClient client = EsClientUtil.getClient();
        System.out.println("ok!!!!");
    }

}
