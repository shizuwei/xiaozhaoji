/**
 * xiaozhaoji.com Inc. Copyright (c) 2014-2016 All Rights Reserved.
 */

package com.xiaozhaoji.crawler;

import com.xiaozhaoji.crawler.processor.TalkPageProcessor;
import com.xiaozhaoji.service.WebCrawlerService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import us.codecraft.webmagic.Spider;
import lombok.extern.slf4j.Slf4j;

/**
 * @title WebCrawler
 * @desc TODO
 * @author shizuwei
 * @date 2016年1月29日
 * @version 1.0
 */
@Slf4j
public class WebCrawler {
    public static void main(String[] args) {
        log.info("WebCrawler starting ...");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        WebCrawlerService webCrawlerService = (WebCrawlerService) context.getBean(WebCrawlerService.class);
        TalkPageProcessor talkPageProcessor = new TalkPageProcessor();
        talkPageProcessor.setWebCrawlerService(webCrawlerService);
        Spider.create(talkPageProcessor).startUrls(TalkPageProcessor.getStartUrls())
            .scheduler(TalkPageProcessor.getScheduler()).addPipeline(TalkPageProcessor.getPipeLine()).thread(10).run();
        log.info("WebCrawler finished ...");
    }
}
