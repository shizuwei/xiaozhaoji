package com.xiaozhaoji.service.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NewsDto {
    Long id;
    String title;
    String content;
    String srcUrl;
    Integer click;
    Date addTime;
    String srcName;
    Integer category;
}
