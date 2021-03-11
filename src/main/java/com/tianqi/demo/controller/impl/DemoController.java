package com.tianqi.demo.controller.impl;

import com.tianqi.demo.pojo.Demo;
import com.tianqi.common.controller.impl.BaseController;
import com.tianqi.demo.service.IDemoService;
import com.tianqi.demo.controller.IDemoController;
import org.springframework.web.bind.annotation.*;

/**
 * 测试表(Demo)表控制层
 *
 * @author makejava
 * @since 2021-03-05 16:54:56
 */
@RestController
@RequestMapping("demo")
public class DemoController extends BaseController<IDemoService, Demo> implements IDemoController {

}
