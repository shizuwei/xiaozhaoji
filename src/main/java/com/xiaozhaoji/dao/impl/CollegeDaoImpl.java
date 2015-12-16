package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.CollegeDao;
import com.xiaozhaoji.dao.po.College;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "collegeDao")
public class CollegeDaoImpl extends SpringCommonDao implements CollegeDao {

    @Override
    public College getCollegeById(Long id) {
        String sql = "select * from college where id=:id and valid = 1";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap,
            new BeanPropertyRowMapper<College>(College.class));
    }

    @Override
    public List<College> getCollegeByCityId(Long cityId) {
        String sql = null;
        Map<String, Object> paramMap = null;
        if (cityId != null) {
            sql = "select * from college where city_id=:cityId and valid = 1";
            paramMap = Maps.newHashMap();
            paramMap.put("cityId", cityId);
        } else {
            sql = "select * from college where valid = 1";
        }
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<College>(College.class));
    }

    @Override
    public List<College> getCollegeByAreaId(Long areaId) {

        String sql =
            "select col.name,col.id,col.short_name from college col join city on city.id = col.city_id where city.area_id = :areaId and col.valid = 1 and city.valid = 1";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("areaId", areaId);
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<College>(College.class));

    }

    @Override
    public Long getCityIdByCollegeId(Long id) {

        String sql = "select city_id from college where id = :id ";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, Long.class);

    }

    @Override
    public String getCollegeNameById(Long id) {
        if (id == null)
            return null;
        String sql = "select name from college where id=:id and valid = 1";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, String.class);
    }
}
