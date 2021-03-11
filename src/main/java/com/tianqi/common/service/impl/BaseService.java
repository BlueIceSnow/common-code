package com.tianqi.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianqi.common.dao.IBaseDAO;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.RestResult;
import com.tianqi.common.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务层抽象
 *
 * @author yuantianqi
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseService<DAO extends IBaseDAO<DO>, DO extends BaseDO>
        implements IBaseService<DO> {
    private DAO dao;

    @Autowired
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    /**
     * 获取实体
     *
     * @param id 请求参数
     * @return
     */
    @Override
    public RestResult<DO> getEntity(String id) {
        DO aDo = dao.selectByPrimaryKey(id);
        return RestResult.<DO>builder()
                .withData(aDo)
                .withIsOk(true)
                .withStatus(StatusEnum.OK)
                .build();
    }

    /**
     * 根据参数查询实体
     *
     * @param entity 请求参数
     * @return
     */
    @Override
    public RestResult<List<DO>> listEntity(DO entity) {
        List<DO> entities = dao.select(entity);
        return RestResult.<List<DO>>builder()
                .withData(entities)
                .withStatus(StatusEnum.OK)
                .withIsOk(true)
                .build();
    }

    /**
     * 分页查询实体
     *
     * @param entity 请求参数
     * @param page   当前页
     * @param size   每页大小
     * @return
     */
    @Override
    public RestResult<DO> listPageEntity(DO entity, int page, int size) {
        PageHelper.startPage(page, size);
        List<DO> result = dao.select(entity);
        PageInfo<DO> pageInfo = new PageInfo<>(result);
        return RestResult.<DO>builderPage()
                .withTotal(pageInfo.getTotal())
                .withRows(pageInfo.getList())
                .withStatus(StatusEnum.OK)
                .build();
    }

    /**
     * 保存实体
     *
     * @param entity
     * @return
     */
    @Override
    public RestResult<DO> save(DO entity) {
        int i = dao.insertSelective(entity);
        if (i != 0) {
            return RestResult.<DO>builder()
                    .withData(entity)
                    .withIsOk(true)
                    .withStatus(StatusEnum.OK)
                    .build();
        }
        return RestResult.<DO>builder()
                .withData(entity)
                .withIsOk(true)
                .withStatus(StatusEnum.OPTION_ERROR)
                .build();
    }

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    @Override
    public RestResult<DO> update(DO entity) {

        int i = dao.updateByPrimaryKeySelective(entity);
        if (i != 0) {
            return RestResult.<DO>builder()
                    .withData(entity)
                    .withIsOk(true)
                    .withStatus(StatusEnum.OK)
                    .build();
        }

        return RestResult.<DO>builder()
                .withData(entity)
                .withIsOk(true)
                .withStatus(StatusEnum.OPTION_ERROR)
                .build();
    }

    /**
     * 分页删除实体
     *
     * @param entity
     * @param page
     * @param size
     * @param id
     * @return
     */
    @Override
    public RestResult<DO> removeByPage(DO entity, int page, int size, String id) {

        int i = dao.deleteByPrimaryKey(id);
        if (i != 0) {
            RestResult<DO> doPageResult = listPageEntity(entity, page, size);
            return doPageResult;
        }
        return RestResult.<DO>builderPage()
                .withStatus(StatusEnum.DELETE_ERROR)
                .withTotal(0)
                .withRows(new ArrayList<>())
                .build();
    }

    /**
     * 删除实体通过ID
     *
     * @param id
     * @return
     */
    @Override
    public RestResult<List<DO>> remove(DO entity, String id) {
        int i = dao.deleteByPrimaryKey(id);
        if (i != 0) {
            RestResult<List<DO>> listRestResult = listEntity(entity);
            return listRestResult;
        }
        return RestResult.<List<DO>>builder()
                .withStatus(StatusEnum.DELETE_ERROR)
                .withIsOk(false)
                .withData(new ArrayList<>())
                .build();
    }
}
