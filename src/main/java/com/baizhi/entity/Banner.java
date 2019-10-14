package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Banner {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "描述")
    private String desc;
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Excel(name = "日期", format = "yyyy-MM-dd", width = 20)
    private Date create_date;
    @Excel(name = "图片", type = 2, width = 30, height = 20, needMerge = true)
    private String img_path;
}
