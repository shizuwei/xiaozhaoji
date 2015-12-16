/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2015 All Rights Reserved.
 */

package com.xiaozhaoji.dao;

import com.xiaozhaoji.dao.po.Area;

import java.util.List;

public interface AreaDao {

    public final static Long DEFAULT_AREA_ID = 1L;

    String getAreaNameById(Long id);

    Area getAreaById(Long id);

    List<Area> getAll();

    Long getAreaIdByAreaName(String name);
}
