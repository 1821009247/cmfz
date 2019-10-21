package com.baizhi;

import com.baizhi.entity.*;
import com.baizhi.mapper.*;
import com.baizhi.service.AdminService;
import com.baizhi.service.ArticleService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.InetAddress;
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
        Date createDate = article.getCreateDate();
        System.out.println(createDate);
        /* articleMapper.update(article);*/
    }

    @Test
    public void name1() throws IOException {
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("192.168.118.33"), 9300);
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(transportAddress);
        List<Article> all = articleMapper.findAll();
        for (Article article : all) {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("title", article.getTitle())
                    .field("author", article.getAuthor())
                    .field("content", article.getContent())
                    .field("status", article.getStatus())
                    .field("id", article.getId())
                    .endObject();
            IndexResponse indexResponse = transportClient.prepareIndex("cmfz", "article").setSource(xContentBuilder).get();
            System.out.println(indexResponse.status());
        }

    }

    @Autowired
    ArticleService articleService;

    @Test
    public void name2() {
        List<Article> articles = articleService.queryByes("国");
        for (Article article : articles) {
            System.out.println(article);

        }
    }
}
