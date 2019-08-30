package com.yuqi.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuqi.demo.common.util.PropertiesUtil;
import com.yuqi.demo.dao.TestMapper;
import com.yuqi.demo.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ProjectServiceTest {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private ITestService iTestService;

    @Test
    public void getPropertiesTest() {
        PropertiesUtil propertiesUtil = new PropertiesUtil("md5.properties");
        System.out.println(propertiesUtil.getProperty("source.1001"));
        System.out.println(propertiesUtil.getProperties().getProperty("source.1001"));
        System.out.println(propertiesUtil.getProperties().get("source.1001"));
    }

    @Test
    public void getProjectListTest() {
        //com.yuqi.demo.entity.Test test=testMapper.selectById("1");
//        com.yuqi.demo.entity.Test test =new com.yuqi.demo.entity.Test();
//        test.setDdDd("修改ddd");
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(2);
        testMapper.selectList(new QueryWrapper<com.yuqi.demo.entity.Test>().in("id",list));

    }
}
