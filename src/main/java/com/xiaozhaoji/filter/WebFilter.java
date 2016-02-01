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
            // 从参数获取areaId,cityId,collegeId
            if (StringUtils.isNotBlank(req.getParameter("areaId"))) {
                Long areaId = Long.parseLong(req.getParameter("areaId"));
                this.setArea(ctx, areaId);
            }

            if (StringUtils.isNotBlank(req.getParameter("collegeId"))) {
                this.setCollege(ctx, Long.parseLong(req.getParameter("collegeId")));
            }

            if (StringUtils.isNotBlank(req.getParameter("cityId"))) {
                this.setCity(ctx, Long.parseLong(req.getParameter("cityId")));
            }

            if (ctx.getAreaId() == null) {
                // cookie中没有，使用ip通过httpClient来获取
                String clientIp = NetworkUtil.getIpAddress(req);
                log.debug("clientIp = {}", clientIp);
                TaoBaoIpInnerData data = getPosDataByIp(clientIp);

                if (data != null) {
                    // 获取后，放入ctx
                    Long cityId = cityDao.getCityIdByCityName(data.getCity());
                    Long areaId = areaDao.getAreaIdByAreaName(data.getArea());
                    this.setCity(ctx, cityId);
                    this.setArea(ctx, areaId);
                } else {
                    ctx.setAreaId(null);
                    ctx.setCityId(null);
                }
            }
            session.setAttribute(WebContext.CTX_NAME, ctx);
        } else {
            // 如果areaId变了,cityId,collegeId都无效,依次类推
            getParamsFromReq(ctx, req);
        }

        log.info("session id = {}, ctx = {}", session.getId(), ctx);
        chain.doFilter(request, response);
    }

    private void getParamsFromReq(WebContext ctx, HttpServletRequest req) {
        if (StringUtils.isNotBlank(req.getParameter("areaId"))) {
            Long areaId = Long.parseLong(req.getParameter("areaId"));
            if (areaId != null && (ctx.getAreaId() == null || !ctx.getAreaId().equals(areaId))) {
                this.setArea(ctx, areaId);
                this.setCity(ctx, null);
                this.setCollege(ctx, null);
            } else {
                if (StringUtils.isNotBlank(req.getParameter("cityId"))) {
                    Long cityId = Long.parseLong(req.getParameter("cityId"));
                    if (cityId != null && (ctx.getCityId() == null || !ctx.getCityId().equals(cityId))) {
                        this.setCity(ctx, cityId);
                        this.setCollege(ctx, null);
                    } else {
                        if (StringUtils.isNotBlank(req.getParameter("collegeId"))) {
                            Long collegeId = Long.parseLong(req.getParameter("collegeId"));
                            if (collegeId != null
                                && (ctx.getCollegeId() == null || !ctx.getCollegeId().equals(collegeId))) {
                                this.setCollege(ctx, collegeId);
                            }
                        }

                    }
                }
            }
        }
    }

    private void setArea(WebContext ctx, Long id) {
        ctx.setAreaId(id);
        if (id != null) {
            ctx.setAreaName(this.areaDao.getAreaNameById(id));
        } else {
            ctx.setAreaName(StringUtils.EMPTY);
        }
    }

    private void setCity(WebContext ctx, Long id) {
        ctx.setCityId(id);
        if (id != null) {
            ctx.setCityName(this.cityDao.getCityNameById(id));
        } else {
            ctx.setCityName(StringUtils.EMPTY);
        }
    }

    private void setCollege(WebContext ctx, Long id) {
        ctx.setCollegeId(id);
        if (id != null) {
            ctx.setCollegeName(this.collegeDao.getCollegeNameById(id));
        } else {
            ctx.setCollegeName(StringUtils.EMPTY);
        }
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
