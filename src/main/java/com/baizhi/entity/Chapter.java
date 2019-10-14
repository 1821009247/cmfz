package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Chapter {
    private String id;
    private String filepath;//音频路径
    private String title;//标题
    private String size;//大小
    private String longs;//时长
    private Date publish_date;//上传时间
    private String album_id;
}
