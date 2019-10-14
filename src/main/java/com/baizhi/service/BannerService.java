package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    public List<Banner> findAll(Integer page, Integer rows);

    public Integer count();

    public void save(Banner banner);

    public Banner findOne(String id);

    public void delete(Banner banner);

    public void updateImg(Banner banner);

    public void update(Banner banner);

    public List<Banner> select();
}
