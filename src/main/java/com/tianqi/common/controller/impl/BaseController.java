package com.tianqi.common.controller.impl;

import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.RestResult;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * 基础控制层
 *
 * @author yuantianqi
 */
public abstract class BaseController<Service extends IBaseService, DO extends BaseDO>
        implements IBaseController<DO> {
    protected Service service;

    @Autowired
    public void setBaseBiz(Service service) {
        this.service = service;
    }

    @Override
    public RestResult<DO> getEntity(String id) {
        return service.getEntity(id);
    }

    @Override
    public RestResult<List<DO>> listEntity(DO entity) {
        return service.listEntity(entity);
    }

    @Override
    public RestResult<DO> listPageEntity(DO entity, int page, int size) {
        return service.listPageEntity(entity, page, size);
    }

    @Override
    public RestResult<DO> save(DO entity) {
        entity.setId(Timestamp.from(Instant.now()).toString());
        return service.save(entity);
    }

    @Override
    public RestResult<DO> update(DO entity) {
        return service.update(entity);
    }

    @GetMapping("page/{id}")
    @Override
    public RestResult<DO> removeByPage(DO entity, int page, int size, String id) {
        return service.removeByPage(entity, page, size, id);
    }

    @Override
    public RestResult<List<DO>> remove(DO entity, String id) {
        return service.remove(entity, id);
    }
}
