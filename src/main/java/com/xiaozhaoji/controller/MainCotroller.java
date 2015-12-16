package com.xiaozhaoji.controller;

import com.google.common.collect.Maps;
import com.xiaozhaoji.controller.dto.request.TalkRequestDto;
import com.xiaozhaoji.dao.po.WebContext;
import com.xiaozhaoji.service.AreaService;
import com.xiaozhaoji.service.CityService;
import com.xiaozhaoji.service.CollegeService;
import com.xiaozhaoji.service.NewsService;
import com.xiaozhaoji.service.TalkService;
import com.xiaozhaoji.service.dto.TalkDto;
import com.xiaozhaoji.service.dto.request.Page;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    @Resource
    private NewsService newsService;

    @RequestMapping(value = "talk/index.do", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute TalkRequestDto talkRequestDto, HttpServletRequest request,
        HttpServletResponse response) {

        WebContext ctx = (WebContext) request.getSession().getAttribute(WebContext.CTX_NAME);

        // 地区的城市列表
        Map<String, Object> model = Maps.newHashMap();
        getArea(ctx, model);
        getCollege(ctx, model);
        getNews(ctx, model, 10);
        // 学校的宣讲会列表
        Page page = new Page();
        page.setCurPage(talkRequestDto.getPage());
        List<TalkDto> talkList = talkService.list(ctx.getCollegeId(), page);
        log.debug("page={}, list = {},", page, talkList);
        model.put("talks", talkList);
        model.put("page", page);
        model.put("ctx", ctx);

        return new ModelAndView(new InternalResourceView("/default/talk.jsp"), model);
    }

    @RequestMapping(value = "talk/get.do", method = RequestMethod.GET)
    public ModelAndView talk(@ModelAttribute TalkRequestDto talkRequestDto, HttpServletRequest request,
        HttpServletResponse response) {
        WebContext ctx = (WebContext) request.getSession().getAttribute(WebContext.CTX_NAME);
        Map<String, Object> model = Maps.newHashMap();
        getArea(ctx, model);
        Long id = talkRequestDto.getId();
        TalkDto talk = talkService.getById(id);
        model.put("talk", talk);
        return new ModelAndView(new InternalResourceView("/default/content.jsp"), model);
    }

    private void getArea(WebContext ctx, Map<String, Object> model) {
        model.put("areas", areaService.getAll(false));
        model.put("curArea", areaService.getAreaById(ctx.getAreaId(), true));
    }

    private void getNews(WebContext ctx, Map<String, Object> model, Integer pageNum) {
        Page pageDto = new Page();
        if (pageNum != null) {
            pageDto.setPageElementCount(pageNum);
        } else {
            pageDto.setPageElementCount(10);
        }
        model.put("newsList", newsService.list(pageDto));
    }

    private void getCollege(WebContext ctx, Map<String, Object> model) {
        if (ctx.getCityId() != null) {
            model.put("colleges", collegeService.getCollegeByCityId(ctx.getCityId()));
        } else {
            model.put("colleges", collegeService.getCollegeByAreaId(ctx.getAreaId()));
        }
    }

}
