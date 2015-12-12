/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2015 All Rights Reserved.
 */

package com.xiaozhaoji.dao;

import com.xiaozhaoji.dao.po.College;

import java.util.List;

public interface CollegeDao {
    College getCollegeById(Long id);

    List<College> getCollegeByCityId(Long cityId);

    List<College> getCollegeByAreaId(Long areaId);
}
