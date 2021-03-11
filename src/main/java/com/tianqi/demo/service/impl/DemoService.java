package com.tianqi.demo.service.impl;

import com.tianqi.demo.pojo.Demo;
import com.tianqi.demo.dao.IDemoDAO;
import com.tianqi.demo.service.IDemoService;
import com.tianqi.common.service.impl.BaseService;
import org.springframework.stereotype.Service;

/**
 * 测试表(Demo)表服务实现类
 *
 * @author makejava
 * @since 2021-03-05 16:54:54
 */
@Service
public class DemoService extends BaseService<IDemoDAO, Demo> implements IDemoService {

}
