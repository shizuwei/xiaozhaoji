package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.AreaDao;
import com.xiaozhaoji.dao.po.Area;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import lombok.NonNull;

@Repository
public class AreaDaoImpl extends SpringCommonDao implements AreaDao {

    @Override
    public Area getAreaById(@NonNull Long id) {
        String sql = "select * from area where id=:id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, new BeanPropertyRowMapper<Area>(Area.class));
    }

    @Override
    public List<Area> getAll() {
        return this.getNamedJdbcTemplate().query("select * from area", new BeanPropertyRowMapper<Area>(Area.class));
    }

    @Override
    public Long getAreaIdByAreaName(String name) {

        String sql = "select id from area where name like :name";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("name", name + '%');
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, Long.class);

    }
}
