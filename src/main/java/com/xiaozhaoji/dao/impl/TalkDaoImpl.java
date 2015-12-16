/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2015 All Rights Reserved.
 */

package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.TalkDao;
import com.xiaozhaoji.dao.po.Talk;
import com.xiaozhaoji.service.dto.request.Page;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        if (id == null)
            return 0;
        String sql = "update talk set click = click + 1 where id = :id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().update(sql, paramMap);

    }

    @Override
    public List<Talk> list(Long collegeId, Date startTime, Date endTime, Page page) {

        String countSql =
            "select count(id) from talk where college_id = :collegeId and add_time >= :startTime and add_time < :endTime";

        String sql =
            "select id,college_id,title,hold_time,address,add_time,src_url,src_name,click from talk where college_id = :collegeId and add_time >= :startTime and add_time < :endTime order by add_time desc limit :start,:count";
        Map<String, Object> paramMap = Maps.newHashMap();

        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        paramMap.put("collegeId", collegeId);

        Integer cnt = this.getNamedJdbcTemplate().queryForObject(countSql, paramMap, Integer.class);
        log.debug(" cnt = {}", cnt);
        page.setTotalElementCount(cnt);
        log.debug("limit {},{}", page.getFirstNumber(), page.getCurPageElementCount());
        paramMap.put("start", page.getFirstNumber());
        paramMap.put("count", page.getCurPageElementCount());
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<Talk>(Talk.class));

    }
}
