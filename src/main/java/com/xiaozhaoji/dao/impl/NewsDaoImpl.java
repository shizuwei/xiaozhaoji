package com.xiaozhaoji.dao.impl;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.NewsDao;
import com.xiaozhaoji.dao.po.News;
import com.xiaozhaoji.service.dto.request.Page;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NewsDaoImpl extends SpringCommonDao implements NewsDao {

    @Override
    public News getById(Long id) {
        String sql = "select * from news where id=:id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().queryForObject(sql, paramMap, new BeanPropertyRowMapper<News>(News.class));

    }

    @Override
    public int addClick(Long id) {
        if (id == null)
            return 0;
        String sql = "update news set click = click + 1 where id = :id";
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", id);
        return this.getNamedJdbcTemplate().update(sql, paramMap);

    }

    @Override
    public List<News> list(Page page) {
        String countSql = "select count(id) from news";
        String sql = "select * from news order by add_time desc limit :start,:count";
        Map<String, Object> paramMap = Maps.newHashMap();

        Integer cnt = this.getNamedJdbcTemplate().queryForObject(countSql, paramMap, Integer.class);
        page.setTotalElementCount(cnt);
        paramMap.put("start", page.getFirstNumber());
        paramMap.put("count", page.getCurPageElementCount());
        return this.getNamedJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<News>(News.class));
    }

}
