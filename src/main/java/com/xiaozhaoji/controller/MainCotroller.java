package com.xiaozhaoji.controller;

import com.google.common.collect.Maps;
import com.xiaozhaoji.controller.dto.request.TalkRequestDto;
import com.xiaozhaoji.dao.po.WebContext;
import com.xiaozhaoji.service.AreaService;
import com.xiaozhaoji.service.CityService;
import com.xiaozhaoji.service.CollegeService;
import com.xiaozhaoji.service.TalkService;
import com.xiaozhaoji.service.dto.TalkDto;
import com.xiaozhaoji.service.dto.request.PageDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;
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
    @Resource
    private TalkService talkService;

    @RequestMapping(value = "talk.do", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute TalkRequestDto talkRequestDto, HttpServletRequest request,
        HttpServletResponse response) {

        WebContext ctx = (WebContext) request.getSession().getAttribute(WebContext.CTX_NAME);

        // 地区的城市列表
        Map<String, Object> model = Maps.newHashMap();
        model.put("area", areaService.getAreaById(ctx.getAreaId(), true));

        // 学校的宣讲会列表
        PageDto page = new PageDto();
        page.setCurPage(talkRequestDto.getPage());
        List<TalkDto> talkList = talkService.list(ctx.getCityId(), page);
        model.put("talks", talkList);

        return new ModelAndView(new InternalResourceView("/index.jsp"), model);
    }

}
