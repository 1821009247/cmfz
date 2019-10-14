package com.baizhi.mapper;

import com.baizhi.entity.EchartsMap;

import java.util.List;

public interface UserMapper {
    public List<EchartsMap> findAll();

    public List<Integer> count();
}
