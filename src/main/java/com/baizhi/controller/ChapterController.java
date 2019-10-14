package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.ChapterService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    AlbumMapper albumMapper;

    @RequestMapping("findAll")
    public Map<String, Object> queryAll(String album_id, Integer page, Integer rows) {
        Map<String, Object> map = chapterService.findAll(album_id, page, rows);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Chapter chapter, String oper, HttpSession session, String album_id) {
        if (oper.equals("add")) {

            String s = UUID.randomUUID().toString();
            chapter.setId(s);
            chapter.setAlbum_id(album_id);
            chapter.setPublish_date(new Date());

            Integer integer = chapterService.count1(album_id);
            Album album = new Album();
            album.setId(album_id);
            album.setCount(integer + 1);
            chapterService.save(chapter, session);
            albumMapper.updateCount(album);
            return s;
        } else if (oper.equals("del")) {
            String[] split = chapter.getId().split(",");
            for (String s : split) {
                chapter.setId(s);
                Integer integer = chapterService.count1(album_id);
                Album album = new Album();
                album.setId(album_id);
                album.setCount(integer - 1);
                chapterService.delete(chapter);
                albumMapper.updateCount(album);
                return null;
            }
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile filepath, String chapterId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/mp3");

        File file = new File(realPath);

        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = filepath.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            filepath.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(filepath);
        Chapter chapter = new Chapter();
        chapter.setId(chapterId);
        chapter.setFilepath(newFileName);
        contextLoads(chapter, session);
        chapterService.updatef(chapter);
    }

    @RequestMapping("download")
    public void download(String filepath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取目标文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/mp3");
        //读入
        FileInputStream fis = new FileInputStream(new File(realPath, filepath));

        //写出
        ServletOutputStream os = response.getOutputStream();
        //设置响应头
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filepath, "utf-8"));

        IOUtils.copy(fis, os);
        //关流
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(os);

    }

    public void contextLoads(Chapter chapter, HttpSession session) {
        String filepath = chapter.getFilepath();
        //获取文件位置
        String realPath = session.getServletContext().getRealPath("/mp3/" + filepath);
        System.out.println(realPath);
        File file = new File(realPath);
        //获取文件大小  单位是字节 byte
        long length = file.length();
        //获取音频时长 单位是秒      AudioFile类需要引入额外依赖 jaudiotagger
        AudioFile read = null;
        try {
            read = AudioFileIO.read(file);
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioHeader header = read.getAudioHeader();
        int trackLength = header.getTrackLength();
        //获取分钟数
        Integer m = trackLength / 60;
        //获取秒秒数
        Integer s = trackLength % 60;
        //将文件大小强转成double类型
        double size = (double) length;

        //获取文件大小 单位是MB
        double ll = size / 1024 / 1024;
        //取double小数点后两位  四舍五入
        BigDecimal bg = new BigDecimal(ll).setScale(2, RoundingMode.UP);
        chapter.setSize(bg + "MB");
        chapter.setLongs(m + "分" + s + "秒");
        chapterService.updateFilepath(chapter);
        System.out.println(bg);
        System.out.println(m + s);
    }
}
