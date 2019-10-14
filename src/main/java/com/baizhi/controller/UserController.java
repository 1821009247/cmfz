package com.baizhi.controller;

import com.baizhi.entity.EchartsMap;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userServicel;

    @RequestMapping("/findAll")
    public List<EchartsMap> findAll() {
        List<EchartsMap> all = userServicel.findAll();
        return all;
    }

    @RequestMapping("/findAll2")
    public List<Integer> findAll2() {
        List<Integer> count = userServicel.count();
        return count;
    }

    @RequestMapping("send")
    public void aa(String information) {
        GoEasy goEasy = new GoEasy("rest-hangzhou.goeasy.io", "BC-ad158fc1bc604d6cb74008b24def505e");
        goEasy.publish("show", information);
    }
}
