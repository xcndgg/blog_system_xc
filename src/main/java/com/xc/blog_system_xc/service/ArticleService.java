package com.xc.blog_system_xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;

import java.util.List;

public interface ArticleService {
    PageInfo<Article> selectArtiicByPage(int page, int count);

    List<Article> getHeartArticles();

    PageInfo<Article> selectArticleByLike(int page,int count,String content);

    Article getArticleDetail(int id);

    List<Article> getArticleByFive();

    void deleteArticleAllInfo(int id);

    void publishArticle(Article article);

    void updateArticleWithId(Article article);

    void updateCategory(String name, String name1);

    void updateContentByCid(Article temp);
}
