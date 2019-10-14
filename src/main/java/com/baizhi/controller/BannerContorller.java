package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("banner")
public class BannerContorller {
    @Autowired
    BannerService bannerService;

    @RequestMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        Integer count = bannerService.count();

        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        List<Banner> all = bannerService.findAll(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", all);
        map.put("records", count);
        map.put("page", page);
        map.put("total", total);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Banner banner, String oper, HttpSession session) {
        if (oper.equals("add")) {
            String s = UUID.randomUUID().toString();
            banner.setId(s);

            Date date = new Date();
            banner.setCreate_date(date);

            bannerService.save(banner);
            return s;
        } else if (oper.equals("del")) {
            String[] split = banner.getId().split(",");
            for (String s : split) {
                banner.setId(s);
                bannerService.delete(banner);
            }
            return null;
        } else if (oper.equals("edit")) {
            Date date = new Date();
            banner.setCreate_date(date);
            bannerService.update(banner);

        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile img_path, String bannerId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");
        System.out.println(img_path);
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = img_path.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            img_path.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Banner banner = bannerService.findOne(bannerId);
        Banner banner1 = banner.setImg_path(newFileName);
        System.out.println(banner1);
        bannerService.updateImg(banner1);
        edit(banner, "asd", session);
    }

    @RequestMapping("select")
    public List<Banner> select(HttpServletResponse response) throws IOException {
        List<Banner> select = bannerService.select();
        FileInputStream fis = new FileInputStream(new File("G:\\导出/easy.xls"));

        //写出
        ServletOutputStream os = response.getOutputStream();
        //设置响应头
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("easy.xls", "utf-8"));

        IOUtils.copy(fis, os);
        //关流
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(os);

        return select;
    }
}

