package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.CollegeDao;
import com.xiaozhaoji.dao.po.College;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CollegeDaoImpl extends SpringCommonDao implements CollegeDao {

    @Override
    public College getCollegeById(Long id) {

        String sql = "select * from college where id=:id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap,
            new BeanPropertyRowMapper<College>(College.class));

    }

    @Override
    public List<College> getCollegeByCityId(Long cityId) {

        String sql = "select * from college where city_id=:cityId";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("cityId", cityId);
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<College>(College.class));

    }

    @Override
    public List<College> getCollegeByAreaId(Long areaId) {

        String sql = "select * from college join city on city.id = college.city_id where city.area_id = :areaId";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("areaId", areaId);
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<College>(College.class));

    }
}
