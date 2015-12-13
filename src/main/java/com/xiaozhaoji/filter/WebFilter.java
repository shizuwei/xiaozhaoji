package com.xiaozhaoji.filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.AreaDao;
import com.xiaozhaoji.dao.CityDao;
import com.xiaozhaoji.dao.CollegeDao;
import com.xiaozhaoji.dao.po.WebContext;
import com.xiaozhaoji.utils.HttpClientUtils;
import com.xiaozhaoji.utils.JacksonUtil;
import com.xiaozhaoji.utils.NetworkUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Servlet Filter implementation class WebFilter
 */
@Slf4j
public class WebFilter implements Filter {

    private CityDao cityDao;
    private AreaDao areaDao;
    private CollegeDao collegeDao;
    private final static String IP_RESOLVING_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public CollegeDao getCollegeDao() {

        return collegeDao;
    }

    public void setCollegeDao(CollegeDao collegeDao) {

        this.collegeDao = collegeDao;
    }

    public CityDao getCityDao() {

        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {

        this.cityDao = cityDao;
    }

    public AreaDao getAreaDao() {

        return areaDao;
    }

    public void setAreaDao(AreaDao areaDao) {

        this.areaDao = areaDao;
    }

    /**
     * Default constructor.
     */
    public WebFilter() {
        log.info("creating bean");
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        log.info("web filter is destroyed! ");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
        ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        WebContext ctx = (WebContext) session.getAttribute(WebContext.CTX_NAME);

        if (ctx == null) {
            ctx = new WebContext();
            log.info("session id = {}", session.getId());

            if (!tryGetIdFromParameter(ctx, req)) {
                // 先从cookie获取
                Cookie areaCookie = getCookieByName(req.getCookies(), WebContext.COOKIE_AREA_ID_NAME);
                Cookie cityCookie = getCookieByName(req.getCookies(), WebContext.COOKIE_CITY_ID_NAME);

                if (areaCookie != null && StringUtils.isNumeric(areaCookie.getValue())) {
                    ctx.setAreaId(Long.parseLong(areaCookie.getValue()));
                }

                if (cityCookie != null && StringUtils.isNumeric(cityCookie.getValue())) {
                    ctx.setCityId(Long.parseLong(cityCookie.getValue()));
                }
            }

            if (ctx.getAreaId() == null || ctx.getCityId() == null) {
                // cookie中没有，使用ip通过httpClient来获取
                String clientIp = NetworkUtil.getIpAddress(req);
                log.debug("clientIp = {}", clientIp);
                TaoBaoIpInnerData data = getPosDataByIp(clientIp);

                if (data != null) {
                    // 获取后，放入ctx
                    Long cityId = cityDao.getCityIdByCityName(data.getCity());
                    Long areaId = areaDao.getAreaIdByAreaName(data.getArea());
                    ctx.setAreaId(areaId);
                    ctx.setCityId(cityId);
                } else {
                    ctx.setAreaId(AreaDao.DEFAULT_AREA_ID);
                    ctx.setCityId(CityDao.DEFAULT_CITY_ID);
                }

                setCookie(res, WebContext.COOKIE_AREA_ID_NAME, ctx.getAreaId().toString());
                setCookie(res, WebContext.COOKIE_CITY_ID_NAME, ctx.getCityId().toString());
            }
            session.setAttribute(WebContext.CTX_NAME, ctx);
        } else {
            if (tryGetIdFromParameter(ctx, req)) {
                setCookie(res, WebContext.COOKIE_AREA_ID_NAME, ctx.getAreaId().toString());
                setCookie(res, WebContext.COOKIE_CITY_ID_NAME, ctx.getCityId().toString());
            }
        }
        chain.doFilter(request, response);
    }

    private static void setCookie(HttpServletResponse res, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    private boolean tryGetIdFromParameter(WebContext ctx, HttpServletRequest req) {

        try {
            if (StringUtils.isNotBlank(req.getParameter("collegeId"))) {
                ctx.setCollegeId(Long.parseLong(req.getParameter("collegeId")));
                if (StringUtils.isNotBlank(req.getParameter("cityId"))) {
                    ctx.setCityId(Long.parseLong(req.getParameter("cityId")));
                } else {
                    ctx.setCityId(this.collegeDao.getCityIdByCollegeId(ctx.getCollegeId()));
                }
                if (StringUtils.isNotBlank(req.getParameter("areaId"))) {
                    ctx.setAreaId(Long.parseLong(req.getParameter("areaId")));
                } else {
                    ctx.setAreaId(this.cityDao.getAreaIdByCityId(ctx.getCityId()));
                }

                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private static Cookie getCookieByName(Cookie[] cookies, String name) {
        if (cookies == null || name == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        log.info("web filter is init! ");
    }

    private static TaoBaoIpData resolveIP(String ip) {
        Map<String, String> params = Maps.newHashMap();
        params.put("ip", ip);
        String data = HttpClientUtils.doGet(IP_RESOLVING_URL, params);
        log.info("ip = {}, data = {}", ip, data);

        TaoBaoIpData tbData = null;
        try {
            tbData = JacksonUtil.str2Obj(data, TaoBaoIpData.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
            log.info("e = {}", e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            log.info("e = {}", e);

        } catch (IOException e) {
            e.printStackTrace();
            log.info("e = {}", e);
        } catch (Throwable e) {
            log.warn("e = {}", e.toString());
        }

        return tbData;
    }

    private static TaoBaoIpInnerData getPosDataByIp(String ip) {
        TaoBaoIpData data = resolveIP(ip);
        if (data == null || data.data == null) {
            return null;
        }
        return data.data;
    }

    @Data
    static class TaoBaoIpInnerData {
        public TaoBaoIpInnerData() {
        }

        private String country;
        private String country_id;
        private String area;
        private Integer area_id;
        private String region;
        private Integer region_id;
        private String city;
        private Integer city_id;
        private String county;
        private Integer county_id;
        private String isp;
        private Integer isp_id;
        private String ip;
    }

    @Data
    static class TaoBaoIpData {
        public TaoBaoIpData() {
        }

        private Integer code;
        private TaoBaoIpInnerData data;
    }

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        // String data = resolveIP("220.181.111.188");
        // log.debug("data = {}", data);
        //
        // TaoBaoIpData tbData = JacksonUtil.str2Obj(data, TaoBaoIpData.class);
        //
        // log.debug("tbData ={}", tbData);
    }
}
