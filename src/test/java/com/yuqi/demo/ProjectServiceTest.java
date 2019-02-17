package com.yuqi.demo;

import com.yuqi.demo.common.util.PropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ProjectServiceTest {

    @Test
    public void addProjectTest(){
        PropertiesUtil propertiesUtil =new PropertiesUtil("md5.properties");
        System.out.println(propertiesUtil.getProperty("source.1001"));
    }

    @Test
    public void getProjectListTest(){
    }
}
