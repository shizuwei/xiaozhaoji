package com.xiaozhaoji.service;

import com.xiaozhaoji.service.dto.TalkDto;
import com.xiaozhaoji.service.dto.request.Page;

import java.util.List;

public interface TalkService {
    TalkDto getById(Long id);

    List<TalkDto> list(Long collegeId, Page pageDto);
}
