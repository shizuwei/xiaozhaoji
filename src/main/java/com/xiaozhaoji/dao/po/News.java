package com.xiaozhaoji.dao.po;

import java.util.Date;

import lombok.Data;

@Data
public class News {
    Long id;
    String title;
    String content;
    String srcUrl;
    Integer click;
    Date addTime;
    String srcName;
    Integer category;
}
