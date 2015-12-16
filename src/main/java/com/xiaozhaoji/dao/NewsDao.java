package com.xiaozhaoji.dao;

import com.xiaozhaoji.dao.po.News;
import com.xiaozhaoji.service.dto.request.Page;

import java.util.List;

public interface NewsDao {
    News getById(Long id);

    int addClick(Long id);

    public List<News> list(Page page);
}
