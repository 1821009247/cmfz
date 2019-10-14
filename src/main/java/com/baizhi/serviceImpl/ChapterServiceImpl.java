package com.baizhi.serviceImpl;


import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public Map<String, Object> findAll(String album_id, Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();

        Integer start = (page - 1) * rows;

        List<Chapter> list = chapterMapper.findAll(album_id, start, rows);

        Integer records = chapterMapper.count();

        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        map.put("records", records);
        map.put("page", page);
        map.put("rows", list);

        return map;
    }

    @Override
    public void save(Chapter chapter, HttpSession session) {
        /* contextLoads(chapter,session);*/
        chapterMapper.save(chapter);
    }

    @Override
    public void updateFilepath(Chapter chapter) {
        chapterMapper.updateFilepath(chapter);
    }

    @Override
    public void delete(Chapter chapter) {
        chapterMapper.delete(chapter);
    }

    @Override
    public Chapter findOne(String id) {
        Chapter one = chapterMapper.findOne(id);
        return one;
    }

    @Override
    public void updatef(Chapter chapter) {
        chapterMapper.updatef(chapter);
    }

    @Override
    public Integer count1(String album_id) {
        Integer integer = chapterMapper.count1(album_id);
        return integer;
    }


}
