package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("getAll")
    public Map<String, Object> getAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("add")
    public void getAll(Article article) {
        System.out.println(article);
        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        articleService.insert(article);
    }

    @RequestMapping("update")
    public void update(Article article) {

        articleService.update(article);
    }
}
