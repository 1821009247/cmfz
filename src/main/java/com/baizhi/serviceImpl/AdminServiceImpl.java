package com.baizhi.serviceImpl;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin login(String username, String password) {
        Admin login = adminMapper.login(username, password);
        return login;
    }

}
