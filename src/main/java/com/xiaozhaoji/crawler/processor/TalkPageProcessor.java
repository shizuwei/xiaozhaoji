/**
 * xiaozhaoji.com Inc. Copyright (c) 2014-2016 All Rights Reserved.
 */

package com.xiaozhaoji.crawler.processor;

import com.google.common.collect.Lists;
import com.xiaozhaoji.service.WebCrawlerService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * @title TalkPageProcessor
 * @desc TODO
 * @author shizuwei
 * @date 2016年1月29日
 * @version 1.0
 */
@Slf4j
public class TalkPageProcessor implements PageProcessor {

    private WebCrawlerService webCrawlerService;

    public void setWebCrawlerService(WebCrawlerService service) {
        this.webCrawlerService = service;
    }

    public WebCrawlerService getWebCrawlerService() {

        return webCrawlerService;
    }

    private final static Map<String, Long> cityMap = new HashMap<String, Long>() {
        private static final long serialVersionUID = -2105704941136590591L;
        {
            put("bj", 1L);
            put("sh", 1L);
            put("gz", 1L);
            put("wh", 1L);
            put("xa", 1L);
            put("cd", 1L);
            put("nj", 1L);
            put("hf", 1L);
            put("jn", 1L);
            put("cs", 1L);
            put("tj", 1L);
            put("zz", 1L);
            put("hz", 1L);
            put("cq", 1L);
            put("dl", 1L);
            put("fz", 1L);
            put("nc", 1L);
            put("cc", 1L);
            put("he", 1L);
        }
    };

    private final static String domain = "xjh.haitou.cc";
    private final static String baseUrl = "http://xjh.haitou.cc/";
    private static List<String> startUrls = null;

    private final static String homePath = "/Users/bjhl/crawler/";

    public static Pipeline getPipeLine() {
        return new FilePipeline(homePath);
    }

    public static Scheduler getScheduler() {
        return new FileCacheQueueScheduler(homePath);
    }

    public static List<String> getStartUrls() {
        if (startUrls == null) {
            startUrls = Lists.newArrayList();
            Collection<String> cities = cityMap.keySet();
            startUrls.add("http://xjh.haitou.cc/article/305223.html");
            for (String city : cities) {
                // startUrls.add(baseUrl + city);
                break;
            }
        }
        return startUrls;
    }

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void process(Page page) {

        // log.info("page = {}", page.getUrl());
        String url = page.getUrl().toString();

        if (url.indexOf("uni-") < 0) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"sidebar-menu\"]//ul").links().all());
        } else {
            page.addTargetRequests(page.getHtml().xpath("//tbody").links().all());

        }

        if (url.indexOf("article") > 0) {
            // log.info("url = {}, count = {}", url, count.addAndGet(1));
            // page.putField("collegeName", page.getHtml().xpath("//span[@class='article-info-content']"));
            log.info(
                "collegeName={}",
                page.getHtml().xpath(
                    "//*[@id='page-wrapper']/div[1]/div[1]/div[2]/div/div[2]/div[1]/p[1]/span[2]/text()"));
            log.info("holdTime={}", page.getHtml().xpath("//*[@id=\"holdTime\"]/span[1]/text()"));
            log.info(
                "address={}",
                page.getHtml().xpath(
                    "//*[@id=\"page-wrapper\"]/div[1]/div[1]/div[2]/div/div[2]/div[1]/p[3]/span[2]/text()"));
            log.info(
                "source={}",
                page.getHtml().xpath(
                    "//*[@id=\"page-wrapper\"]/div[1]/div[1]/div[2]/div/div[2]/div[2]/p[2]/span[2]/text()"));
            log.info(
                "click={}",
                page.getHtml().xpath(
                    "//*[@id=\"page-wrapper\"]/div[1]/div[1]/div[2]/div/div[2]/div[2]/p[3]/span[2]/text()"));
            log.info("source_url={}",
                page.getHtml().xpath("//*[@id=\"page-wrapper\"]/div[1]/div[2]/div[2]/div/a[1]/@href"));
            log.info("title={}", page.getHtml().xpath("//*[@id=\"page-wrapper\"]/div[1]/div[1]/div[1]/h3/text()"));
            log.info("content={}", page.getHtml().xpath("//*[@id=\"page-wrapper\"]/div[1]/div[2]/div[2]"));
        } else {
            page.setSkip(true);
        }

    }

    private Site site =
        Site.me()
            .setRetryTimes(3)
            .setSleepTime(0)
            .setDomain(domain)
            .setUserAgent(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");;

    @Override
    public Site getSite() {
        return site;
    }

}
