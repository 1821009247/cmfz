package com.baizhi.service;


import com.baizhi.entity.Chapter;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ChapterService {
    public Map<String, Object> findAll(String album_id, Integer page, Integer rows);

    public void save(Chapter chapter, HttpSession session);

    public void updateFilepath(Chapter chapter);

    public void delete(Chapter chapter);

    public Chapter findOne(String id);

    public void updatef(Chapter chapter);

    public Integer count1(String album_id);

}
