package com.xiaozhaoji.dao.po;

import java.util.Date;

import lombok.Data;

@Data
public class Talk {
    private Long id;
    private Long collegeId;
    private String content;
    private Date holdTime;
    private String address;
    private Date addTime;
    private String srcUrl;
    private String srcName;
    private Integer click;
    private String title;
}
