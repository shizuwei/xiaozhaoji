package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.AreaDao;
import com.xiaozhaoji.dao.po.Area;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository(value = "areaDao")
public class AreaDaoImpl extends SpringCommonDao implements AreaDao {

    public AreaDaoImpl() {
        log.info("creating bean");
    }

    @Override
    public Area getAreaById(@NonNull Long id) {
        String sql = "select * from area where id=:id and valid = 1";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, new BeanPropertyRowMapper<Area>(Area.class));
    }

    @Override
    public List<Area> getAll() {
        return this.getNamedJdbcTemplate().query("select * from area where valid = 1",
            new BeanPropertyRowMapper<Area>(Area.class));
    }

    @Override
    public Long getAreaIdByAreaName(String name) {

        if (StringUtils.isEmpty(name)) {
            return DEFAULT_AREA_ID;
        }
        String sql = "select id from area where name like :name and valid = 1";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("name", name + '%');
        List<Long> list = this.getNamedJdbcTemplate().queryForList(sql, paramMap, Long.class);
        if (CollectionUtils.isEmpty(list)) {
            return DEFAULT_AREA_ID;
        }
        return list.get(0);
    }

    @Override
    public String getAreaNameById(Long id) {

        String sql = "select name from area where id=:id and valid = 1";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, String.class);

    }
}
