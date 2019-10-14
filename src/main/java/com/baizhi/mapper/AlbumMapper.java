package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {
    public List<Album> findAllChapter(@Param("start") Integer start, @Param("rows") Integer rows);

    public List<Album> findAll(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer count();

    public void save(Album album);

    public void updateImg(Album album);

    public void update(Album album);

    public void delete(Album album);

    public Album findOne(String id);

    public void updateCount(Album album);
}
