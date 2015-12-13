package com.xiaozhaoji.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getTodayStartTime(int offset) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, offset);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        return cal.getTime();
    }

    /**
     * 获取今天零点Date
     */
    public static Date getTodayStartTime() {
        return getTodayStartTime(0);
    }

}
