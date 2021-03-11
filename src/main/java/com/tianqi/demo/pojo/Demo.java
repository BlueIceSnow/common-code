package com.tianqi.demo.pojo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tianqi.common.pojo.BaseDO;

/**
 * Demo表：测试表
 *
 * @author makejava
 * @since 2021-03-05 16:54:50
 */
@Table(name = "demo")
@Data
public class Demo extends BaseDO implements Serializable {
    private static final long serialVersionUID = -80047187428568913L;
    /**
     * 详细信息
     */
    @Column(name = "info")
    private JSONObject info;

}
