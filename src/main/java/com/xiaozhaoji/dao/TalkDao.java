package com.xiaozhaoji.dao;

import com.xiaozhaoji.dao.po.Talk;

import java.util.Date;
import java.util.List;

public interface TalkDao {
    Talk getById(Long id);

    int addClick(Long id);

    public List<Talk> list(Long collegeId, Date startTime, Date endTime, int start, int count);
}
