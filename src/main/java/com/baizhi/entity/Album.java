package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Album {
    private String id;
    private String title;//标题
    private Double score;//评分
    private String author;//作者
    private String beam;//博音
    private Integer count;//章节数
    private Date create_date;//创建时间
    private Date publish_date;//发行时间
    private String cover;//封面路径
    private String content;//内容简介
    private String status;//状态
    /*  private Chapter chapters;*/
}
