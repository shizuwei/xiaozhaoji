package com.xiaozhaoji.service.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AreaDto {
    /**
     * 唯一ID
     */
    private Long id;
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

    /**
     * area下的所有city
     */
    private List<CityDto> cities;
}
