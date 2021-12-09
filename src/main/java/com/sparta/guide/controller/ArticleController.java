package com.sparta.guide.controller;

import com.sparta.guide.domain.Article;
import com.sparta.guide.dto.PostArticleDto;
import com.sparta.guide.dto.GetArticlesDto;
import com.sparta.guide.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @PostMapping("/article")
    public Article setArticle(PostArticleDto.Request request) throws IOException {
        return articleService.setArticle(request);
    }

    @GetMapping("/articles")
    public List<GetArticlesDto.Response> getArticles(){
        List<Article> articles = articleService.getArticles();
        List<GetArticlesDto.Response> response = modelMapper.map(articles, new TypeToken<List<GetArticlesDto.Response>>() {}.getType());
        return response;
    }
}
