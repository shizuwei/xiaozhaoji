/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2015 All Rights Reserved.
 */

package com.xiaozhaoji.dao;

import com.xiaozhaoji.dao.po.City;

import java.util.List;

public interface CityDao {
    public static final Long DEFAULT_CITY_ID = 1L;

    City getCityById(Long id);

    String getCityNameById(Long id);

    List<City> getCityByAreaId(Long areaId);

    Long getCityIdByCityName(String name);

    Long getAreaIdByCityId(Long cityId);

    City getDefaultCity(Long areaId);
}
