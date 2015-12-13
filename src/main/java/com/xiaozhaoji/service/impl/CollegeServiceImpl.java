package com.xiaozhaoji.service.impl;

import com.google.common.collect.Lists;
import com.xiaozhaoji.dao.CollegeDao;
import com.xiaozhaoji.dao.po.College;
import com.xiaozhaoji.service.CollegeService;
import com.xiaozhaoji.service.dto.CollegeDto;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CollegeServiceImpl implements CollegeService {

    @Resource
    private CollegeDao collegeDao;

    @Override
    public CollegeDto getCollegeById(Long id) {
        College college = collegeDao.getCollegeById(id);
        return Po2Dto(college);
    }

    CollegeDto Po2Dto(College po) {
        CollegeDto dto = new CollegeDto();
        dto.setCreateTime(po.getCreateTime());
        dto.setCityId(po.getCityId());
        dto.setId(po.getId());
        dto.setName(po.getName());
        dto.setOrder(po.getOrder());
        dto.setPyName(po.getPyName());
        dto.setShortName(po.getShortName());
        dto.setUpdateTime(po.getUpdateTime());
        dto.setValid(po.getValid());
        return dto;
    }

    @Override
    public List<CollegeDto> getCollegeByAreaId(Long areaId) {

        List<CollegeDto> dtos = Lists.newArrayList();
        List<College> colleges = this.collegeDao.getCollegeByAreaId(areaId);
        log.debug("colleges = {},areaid={}", colleges, areaId);
        for (College college : colleges) {
            dtos.add(Po2Dto(college));
        }
        return dtos;

    }

    @Override
    public List<CollegeDto> getCollegeByCityId(Long cityId) {

        List<CollegeDto> dtos = Lists.newArrayList();
        List<College> colleges = this.collegeDao.getCollegeByCityId(cityId);
        for (College college : colleges) {
            dtos.add(Po2Dto(college));
        }
        return dtos;

    }
}
