package com.xiaozhaoji.service.impl;

import com.google.common.collect.Lists;
import com.xiaozhaoji.dao.NewsDao;
import com.xiaozhaoji.dao.po.News;
import com.xiaozhaoji.service.NewsService;
import com.xiaozhaoji.service.dto.NewsDto;
import com.xiaozhaoji.service.dto.request.Page;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsDao newsDao;

    @Override
    public NewsDto getById(Long id) {
        News news = newsDao.getById(id);
        newsDao.addClick(id);
        return po2Dto(news);

    }

    private NewsDto po2Dto(News po) {
        NewsDto dto = new NewsDto();
        dto.setAddTime(po.getAddTime());
        dto.setCategory(po.getCategory());
        dto.setClick(po.getClick());
        dto.setContent(po.getContent());
        dto.setId(po.getId());
        dto.setSrcName(po.getSrcName());
        dto.setSrcUrl(po.getSrcUrl());
        dto.setTitle(po.getTitle());
        return dto;
    }

    private List<NewsDto> po2Dtos(List<News> pos) {
        List<NewsDto> dtos = Lists.newArrayList();
        for (News po : pos) {
            dtos.add(po2Dto(po));
        }
        return dtos;
    }

    @Override
    public List<NewsDto> list(Page pageDto) {
        List<News> pos = newsDao.list(pageDto);
        return po2Dtos(pos);
    }

}
