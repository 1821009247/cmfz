package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public String login(String username, String password, String code, HttpSession session) {
        String yzm = (String) session.getAttribute("validationCode");

        if (yzm.equals(code)) {
            Admin login = adminService.login(username, password);
            session.setAttribute("username", username);
            if (login != null) {
                return "ok";
            } else {
                return "账号或者密码有误";
            }

        } else {
            return "验证码错误";
        }
    }

    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}
