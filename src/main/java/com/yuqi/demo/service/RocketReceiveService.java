package com.yuqi.demo.service;

import com.yuqi.demo.entity.Test;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//consumerGroup-消费者组名 topic-要消费的主题
@RocketMQMessageListener(consumerGroup = "shop-producer",topic = "order-topic")
public class RocketReceiveService implements RocketMQListener<Test> {
    @Override
    public void onMessage(Test test) {
        log.info("接收到了一个rocketmq信息:{}", test);
    }
}
