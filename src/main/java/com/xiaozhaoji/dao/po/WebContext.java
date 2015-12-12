package com.xiaozhaoji.dao.po;

import lombok.Data;

@Data
public class WebContext {
    public static final String CTX_NAME = "web-ctx";
    public static final String COOKIE_AREA_ID_NAME = "web-ctx-areaid-cookie";
    public static final String COOKIE_CITY_ID_NAME = "web-ctx-cityid-cookie";
    private Long areaId = 1L;
    private Long cityId;
    private Long collegeId;
}
