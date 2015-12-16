package com.xiaozhaoji.service.impl;

import com.google.common.collect.Lists;
import com.xiaozhaoji.dao.TalkDao;
import com.xiaozhaoji.dao.po.Talk;
import com.xiaozhaoji.service.TalkService;
import com.xiaozhaoji.service.dto.TalkDto;
import com.xiaozhaoji.service.dto.request.Page;
import com.xiaozhaoji.utils.DateUtils;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TalkServiceImpl implements TalkService {
    @Resource
    private TalkDao talkDao;

    @Override
    public TalkDto getById(Long id) {
        return po2dto(talkDao.getById(id));
    }

    @Override
    public List<TalkDto> list(Long collegeId, Page pageDto) {
        List<Talk> talks =
            talkDao.list(collegeId, DateUtils.getTodayStartTime(-10), DateUtils.getTodayStartTime(30), pageDto);
        return pos2dtos(talks);
    }

    private TalkDto po2dto(Talk po) {
        TalkDto dto = new TalkDto();
        dto.setCollegeId(po.getCollegeId());
        dto.setClick(po.getClick());
        dto.setTitle(po.getTitle());
        dto.setAddTime(po.getAddTime());
        dto.setAddress(po.getAddress());
        dto.setContent(po.getContent());
        dto.setHoldTime(po.getHoldTime());
        dto.setId(po.getId());
        dto.setSrcName(po.getSrcName());
        dto.setSrcUrl(po.getSrcUrl());
        return dto;
    }

    private List<TalkDto> pos2dtos(List<Talk> pos) {
        List<TalkDto> dtos = Lists.newArrayList();
        for (Talk po : pos) {
            dtos.add(po2dto(po));
        }
        log.debug("news = {}", pos);
        return dtos;
    }
}
