package com.sparta.guide.domain;

import com.sparta.guide.dto.PostArticleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Article extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String imageUrl;

    public Article(PostArticleDto.Request request, String imageUrl) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.imageUrl = imageUrl;
    }
}
