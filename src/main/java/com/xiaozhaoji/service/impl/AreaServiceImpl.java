package com.xiaozhaoji.service.impl;

import com.google.common.collect.Lists;
import com.xiaozhaoji.dao.AreaDao;
import com.xiaozhaoji.dao.po.Area;
import com.xiaozhaoji.service.AreaService;
import com.xiaozhaoji.service.CityService;
import com.xiaozhaoji.service.dto.AreaDto;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaDao areaDao;
    @Resource
    private CityService cityService;

    @Override
    public AreaDto getAreaById(Long id) {
        return getAreaById(id, false);
    }

    @Override
    public AreaDto getAreaById(Long id, boolean loadCites) {

        Area area = areaDao.getAreaById(id);
        AreaDto areaDto = Po2Dto(area);
        if (loadCites) {
            areaDto.setCities(this.cityService.getCityByAreaId(id));
        }
        return areaDto;
    }

    AreaDto Po2Dto(Area po) {
        AreaDto dto = new AreaDto();
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

    List<AreaDto> Pos2Dtos(List<Area> pos) {
        List<AreaDto> dtos = Lists.newArrayList();
        for (Area po : pos) {
            dtos.add(Po2Dto(po));
        }
        return dtos;
    }

    @Override
    public List<AreaDto> getAll(Long id, boolean loadCites) {
        List<AreaDto> dtos = Pos2Dtos(this.areaDao.getAll());
        if (loadCites) {
            for (AreaDto dto : dtos) {
                dto.setCities(this.cityService.getCityByAreaId(id));
            }
        }
        return Pos2Dtos(this.areaDao.getAll());
    }
}
