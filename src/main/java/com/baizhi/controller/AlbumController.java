package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("findAll")
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = albumService.findAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Album album, String oper, HttpSession session) {
        if (oper.equals("add")) {
            String s = UUID.randomUUID().toString();
            album.setId(s);
            album.setCreate_date(new Date());
            album.setPublish_date(new Date());

            albumService.save(album);
            return s;
        } else if (oper.equals("del")) {
            String[] split = album.getId().split(",");
            for (String s : split) {
                album.setId(s);
                albumService.delete(album);
                return null;
            }
        } else {
            album.setCreate_date(new Date());
            album.setPublish_date(new Date());
            albumService.update(album);
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile cover, String albumId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");

        File file = new File(realPath);

        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = cover.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            cover.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Album one = albumService.findOne(albumId);

        Album album = one.setCover(newFileName);
        albumService.updateImg(album);
        edit(one, "asd", session);
    }

}
