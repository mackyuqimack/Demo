package com.yuqi.demo.service.impl;

import com.yuqi.demo.entity.Test;
import com.yuqi.demo.dao.TestMapper;
import com.yuqi.demo.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuqi
 * @since 2019-02-18
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
