package com.yuqi.demo.service;

import com.yuqi.demo.entity.Test;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//consumerGroup-消费者组名 topic-要消费的主题
// consumeModel-消费模式，指定是否顺序消费（默认同步模式，非顺序） messageModel-消息模式，广播模式和集群模式
//广播消费: 每个消费者实例都会收到消息,也就是一条消息可以被每个消费者实例处理；
//集群消费: 一条消息只能被一个消费者实例消费
@RocketMQMessageListener(consumerGroup = "shop-producer",topic = "order-topic")
public class RocketReceiveService implements RocketMQListener<Test> {
    @Override
    public void onMessage(Test test) {
        log.info("接收到了一个rocketmq信息:{}", test);
    }
}
