package com.baizhi.service;

import com.baizhi.entity.EchartsMap;

import java.util.List;

public interface UserService {
    public List<EchartsMap> findAll();

    public List<Integer> count();
}
