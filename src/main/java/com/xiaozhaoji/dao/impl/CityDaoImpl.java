package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.CityDao;
import com.xiaozhaoji.dao.po.City;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CityDaoImpl extends SpringCommonDao implements CityDao {

    @Override
    public City getCityById(Long id) {

        String sql = "select * from city where id=:id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, new BeanPropertyRowMapper<City>(City.class));

    }

    @Override
    public List<City> getCityByAreaId(Long areaId) {

        String sql = "select * from city where area_id=:areaId";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("areaId", areaId);
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<City>(City.class));
    }

    @Override
    public Long getCityIdByCityName(String name) {

        String sql = "select id from city where name like :name";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("name", name + '%');
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, Long.class);

    }

    @Override
    public Long getAreaIdByCityId(Long cityId) {

        String sql = "select area_id from city where id = :cityId";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("cityId", cityId);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, Long.class);

    }
}
