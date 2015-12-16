package com.xiaozhaoji.service;

import com.xiaozhaoji.service.dto.NewsDto;
import com.xiaozhaoji.service.dto.request.Page;

import java.util.List;

public interface NewsService {
    NewsDto getById(Long id);

    List<NewsDto> list(Page pageDto);
}
