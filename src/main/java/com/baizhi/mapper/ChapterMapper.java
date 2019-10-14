package com.baizhi.mapper;


import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    public List<Chapter> findAll(@Param("album_id") String album_id, @Param("start") Integer start, @Param("rows") Integer rows);

    public Integer count();

    public void save(Chapter chapter);

    public void updateFilepath(Chapter chapter);

    public void delete(Chapter chapter);

    public Chapter findOne(String id);

    public void update(Chapter chapter);

    public void updatef(Chapter chapter);

    public Integer count1(@Param("album_id") String album_id);

}
