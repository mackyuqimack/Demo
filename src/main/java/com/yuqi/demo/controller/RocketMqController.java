package com.yuqi.demo.controller;

import com.yuqi.demo.entity.Test;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocketmq")
public class RocketMqController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("send")
    public String send(String content) {
        Test test =new Test();
        test.setId(11);
        test.setName(content);
        test.setDdDd("dd");
        //参数1：指定topic；参数2：指定消息体
        rocketMQTemplate.convertAndSend("order-topic", test);
        return content;
    }
}
