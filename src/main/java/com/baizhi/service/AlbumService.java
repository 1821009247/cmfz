package com.baizhi.service;


import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    public Map<String, Object> findAll(Integer page, Integer rows);

    public void save(Album album);

    public void updateImg(Album album);

    public void update(Album album);

    public void delete(Album album);

    public Album findOne(String id);
}
