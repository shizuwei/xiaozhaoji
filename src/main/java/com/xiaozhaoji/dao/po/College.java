package com.xiaozhaoji.dao.po;

import java.util.Date;

import lombok.Data;

@Data
public class College {

    /**
     * 唯一ID
     */
    private Long id;
    /**
     * 地区ID
     */
    private Long cityId;
    /**
     * 全称
     */
    private String name;
    /**
     * 排序，数字越小排位越靠前,默认值为0
     */
    private Integer order;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 拼音简称
     */
    private String pyName;

    /**
     * 是否有效
     */
    private Boolean valid;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 添加时间
     */
    private Date createTime;
}
