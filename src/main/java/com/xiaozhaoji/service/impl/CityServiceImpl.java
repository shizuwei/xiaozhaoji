package com.xiaozhaoji.service.impl;

import com.google.common.collect.Lists;
import com.xiaozhaoji.dao.AreaDao;
import com.xiaozhaoji.dao.CityDao;
import com.xiaozhaoji.dao.po.City;
import com.xiaozhaoji.service.CityService;
import com.xiaozhaoji.service.CollegeService;
import com.xiaozhaoji.service.dto.CityDto;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class CityServiceImpl implements CityService {

    @Resource
    private AreaDao areaDao;
    @Resource
    private CityDao cityDao;
    @Resource
    private CollegeService collegeService;

    @Override
    public CityDto getCityById(Long id) {
        return getCityById(id, false);
    }

    @Override
    public CityDto getCityById(Long id, boolean loadColleges) {
        City city = cityDao.getCityById(id);
        CityDto cityDto = Po2Dto(city);
        if (loadColleges) {
            cityDto.setColleges(this.collegeService.getCollegeByCityId(id));
        }
        return Po2Dto(city);
    }

    CityDto Po2Dto(City po) {
        CityDto dto = new CityDto();
        dto.setCreateTime(po.getCreateTime());
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
    public List<CityDto> getCityByAreaId(Long areaId) {
        List<CityDto> cityDtos = Lists.newArrayList();
        List<City> cities = this.cityDao.getCityByAreaId(areaId);
        for (City city : cities) {
            CityDto dto = Po2Dto(city);
            cityDtos.add(dto);
        }
        return cityDtos;
    }

}
