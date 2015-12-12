package com.xiaozhaoji.service;

import com.xiaozhaoji.service.dto.CityDto;

import java.util.List;

public interface CityService {

    CityDto getCityById(Long id);

    CityDto getCityById(Long id, boolean loadColleges);

    List<CityDto> getCityByAreaId(Long areaId);

}
