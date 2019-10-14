package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    public List<Banner> findAll(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer count();

    public void save(Banner banner);

    public void updateImg(Banner banner);

    public void update(Banner banner);

    public void delete(Banner banner);

    public Banner findOne(String id);

    public List<Banner> select();
}
