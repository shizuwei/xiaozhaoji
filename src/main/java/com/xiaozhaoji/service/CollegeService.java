package com.xiaozhaoji.service;

import com.xiaozhaoji.service.dto.CollegeDto;

import java.util.List;

public interface CollegeService {

    CollegeDto getCollegeById(Long id);

    List<CollegeDto> getCollegeByAreaId(Long areaId);

    List<CollegeDto> getCollegeByCityId(Long cityId);
}
