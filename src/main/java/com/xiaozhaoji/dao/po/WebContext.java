package com.xiaozhaoji.dao.po;

import com.xiaozhaoji.dao.AreaDao;
import com.xiaozhaoji.dao.CityDao;
import com.xiaozhaoji.dao.CollegeDao;

public class WebContext {
    public static final String CTX_NAME = "web-ctx";
    public static final String COOKIE_AREA_ID_NAME = "web-ctx-areaid-cookie";
    public static final String COOKIE_CITY_ID_NAME = "web-ctx-cityid-cookie";
    private Long areaId = 1L;
    private Long cityId;
    private Long collegeId;

    public Long getAreaId() {

        return areaId;
    }

    public void setAreaId(Long areaId) {

        if (areaId != null) {
            this.areaId = areaId;
        } else {
            this.areaId = AreaDao.DEFAULT_AREA_ID;
        }
    }

    public Long getCityId() {

        return cityId;
    }

    public void setCityId(Long cityId) {
        if (cityId != null) {
            this.cityId = cityId;
        } else {
            this.cityId = CityDao.DEFAULT_CITY_ID;
        }
    }

    public Long getCollegeId() {

        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        if (collegeId != null) {
            this.collegeId = collegeId;
        } else {
            this.collegeId = CollegeDao.DEFULT_COLLEGE_ID;
        }
    }
}
