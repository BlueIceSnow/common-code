package com.tianqi.common.service;


import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.RestResult;

import java.util.List;

/**
 * 基础业务层抽象
 *
 * @author yuantianqi
 */
public interface IBaseService<DO extends BaseDO> {
    /**
     * 查询单个实体
     *
     * @param id 请求参数
     * @return
     */
    RestResult<DO> getEntity(String id);

    /**
     * 查询所有实体
     *
     * @param entity 请求参数
     * @return
     */
    RestResult<List<DO>> listEntity(DO entity);


    /**
     * 查询单个实体
     *
     * @param entity 请求参数
     * @param page   当前页
     * @param size   每页大小
     * @return
     */
    RestResult<DO> listPageEntity(DO entity, int page, int size);

    /**
     * 保存实体
     *
     * @param entity
     * @return
     */
    RestResult<DO> save(DO entity);

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    RestResult<DO> update(DO entity);

    /**
     * 分页删除实体
     *
     * @param entity
     * @param page
     * @param id
     * @return
     */
    RestResult<DO> removeByPage(DO entity, int page, int size, String id);

    /**
     * 删除实体
     *
     * @param id
     * @param entity
     * @return
     */
    RestResult<List<DO>> remove(DO entity, String id);

}
