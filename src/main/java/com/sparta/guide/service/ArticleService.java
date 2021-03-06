package com.sparta.guide.service;

import com.sparta.guide.domain.Article;
import com.sparta.guide.dto.PostArticleDto;
import com.sparta.guide.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final AwsService awsService;

    @Transactional
    public Article setArticle(PostArticleDto.Request request) throws IOException {
        String url = null;
        if(request.getImage() != null) url = awsService.upload(request.getImage());
        Article article = new Article(request, url);
        articleRepository.save(article);

        return article;
    }

    public Article getArticle(Long id){
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

}
