package com.yuqi.demo.common.config;

import com.yuqi.demo.common.Constants;
import com.yuqi.demo.common.interceptor.ValidationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 验证权限拦截器配置
 *
 * @author yuqi
 * @date 2018-12-28 09:27:44
 */
@Configuration
@MapperScan(Constants.DAO)
public class ValidationConfig extends WebMvcConfigurerAdapter  {

    @Bean
    ValidationInterceptor validationInterceptor() {
        return new ValidationInterceptor();
    }

    /**
     * 默认首页配置
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor())
                //添加需要验证权限的请求
                .addPathPatterns("/api/v1/sync/*")
                //排除不需要验证权限的请求
                .excludePathPatterns("/api/v1/role/*");
    }
}
