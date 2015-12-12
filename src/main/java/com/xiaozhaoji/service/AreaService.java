package com.xiaozhaoji.service;

import com.xiaozhaoji.service.dto.AreaDto;

import java.util.List;

public interface AreaService {

    AreaDto getAreaById(Long id);

    AreaDto getAreaById(Long id, boolean loadCites);

    List<AreaDto> getAll(Long id, boolean loadCites);

}
