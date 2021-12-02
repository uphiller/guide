package com.sparta.guide.controller;

import com.sparta.guide.domain.Article;
import com.sparta.guide.dto.ArticleRequestDto;
import com.sparta.guide.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public Article setArticle(ArticleRequestDto articleRequestDto) throws IOException {
        return articleService.setArticle(articleRequestDto);
    }

    @GetMapping("/articles")
    public List<Article> getArticles(){
        return articleService.getArticles();
    }
}
