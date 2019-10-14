package com.baizhi.serviceImpl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //获取数据库中的起始条
        Integer start = (page - 1) * rows;
        //查询出轮播图列表
        List<Album> list = albumMapper.findAll(start, rows);
        //查询出总条数
        Integer records = albumMapper.count();
        //总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        map.put("records", records);
        map.put("page", page);
        map.put("rows", list);

        return map;
    }

    @Override
    public void save(Album album) {

        albumMapper.save(album);
    }

    @Override
    public void updateImg(Album album) {
        albumMapper.updateImg(album);
    }

    @Override
    public void update(Album album) {
        albumMapper.update(album);
    }

    @Override
    public void delete(Album album) {
        albumMapper.delete(album);
    }

    @Override
    public Album findOne(String id) {
        Album one = albumMapper.findOne(id);

        return one;
    }

}
