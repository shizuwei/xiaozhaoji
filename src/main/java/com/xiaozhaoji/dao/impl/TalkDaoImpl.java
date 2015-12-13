/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2015 All Rights Reserved.
 */

package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.TalkDao;
import com.xiaozhaoji.dao.po.Talk;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class TalkDaoImpl extends SpringCommonDao implements TalkDao {

    @Override
    public Talk getById(Long id) {
        String sql = "select * from talk where id=:id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, new BeanPropertyRowMapper<Talk>(Talk.class));
    }

    @Override
    public int addClick(Long id) {

        String sql = "update talk set click = click + 1 where id = :id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().update(sql, paramMap);

    }

    @Override
    public List<Talk> list(Long collegeId, Date startTime, Date endTime, int start, int count) {

        String sql =
            "select * from talk where college_id = :collegeId and add_time >= :startTime and add_time < :endTime order by add_time desc limit :start,:count";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("start", start);
        paramMap.put("count", count);
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        paramMap.put("collegeId", collegeId);
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<Talk>(Talk.class));

    }
}
