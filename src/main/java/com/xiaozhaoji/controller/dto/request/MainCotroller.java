package com.xiaozhaoji.controller.dto.request;

import com.google.common.collect.Maps;
import com.xiaozhaoji.dao.po.WebContext;
import com.xiaozhaoji.service.AreaService;
import com.xiaozhaoji.service.CityService;
import com.xiaozhaoji.service.CollegeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class MainCotroller {

    @Resource
    private AreaService areaService;
    @Resource
    private CityService cityService;
    @Resource
    private CollegeService collegeService;

    @RequestMapping(value = "talk.do", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        WebContext ctx = (WebContext) request.getSession().getAttribute(WebContext.CTX_NAME);

        Map<String, Object> model = Maps.newHashMap();
        model.put("area", areaService.getAreaById(ctx.getAreaId(), true));
        return new ModelAndView(new InternalResourceView("/index.jsp"), model);
    }

}
