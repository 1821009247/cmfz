package com.baizhi;

import com.baizhi.entity.*;
import com.baizhi.mapper.*;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminService adminService;

    @Test
    public void login() {
        Admin lzd = adminService.login("l2zd", "123456");
        if (lzd == null) {
            System.out.println("=======");
        } else {
            System.out.println("++++++++++++");
        }
    }

    @Autowired
    BannerMapper bannerMapper;

    @Test
    public void findAllBanner() {
        List<Banner> all = bannerMapper.findAll(0, 1);
        for (Banner banner : all) {
            System.out.println(banner);
        }
    }

    @Test
    public void save() {
        Banner banner = new Banner();
        banner.setImg_path("1");
        banner.setId("b9b90f2e-1876-46cb-b1e4-46fccc6ce64e");
        bannerMapper.updateImg(banner);
    }

    @Autowired
    AlbumMapper albumMapper;

    @Test
    public void findAllChapters() {
        List<Album> allChapter = albumMapper.findAllChapter(0, 1);
        for (Album album : allChapter) {
            System.out.println(album);
        }
    }

    @Test
    public void findAllAlbum() {
        Chapter chapter = new Chapter();
        chapter.setId("22");
        chapter.setTitle("1221");
        chapter.setSize("2");
        chapter.setLongs("233");
        chapter.setAlbum_id("1");
        chapter.setPublish_date(new Date());
        chapterMapper.save(chapter);
    }

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void insert() {
        Article article = new Article();
        article.setId("3");
        article.setTitle("标题3");
        article.setContent("内容");
        article.setAuthor("作者");
        article.setStatus("y");
        articleMapper.update(article);
        System.out.println("xiaohei");
    }

}
