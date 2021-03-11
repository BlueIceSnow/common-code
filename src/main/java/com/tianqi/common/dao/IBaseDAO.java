package com.tianqi.common.dao;

import com.tianqi.common.pojo.BaseDO;
import tk.mybatis.mapper.common.Mapper;

/**
 * 持久层封装
 *
 * @author yuantianqi
 */
public interface IBaseDAO<DO extends BaseDO> extends Mapper<DO> {

}
