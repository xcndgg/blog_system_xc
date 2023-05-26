package com.xc.blog_system_xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.Statistic;
import com.xc.blog_system_xc.mapper.ArticleMapper;
import com.xc.blog_system_xc.mapper.CommentMapper;
import com.xc.blog_system_xc.mapper.StatisticMapper;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.StatisticService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private StatisticMapper statisticMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public PageInfo<Article> selectArtiicByPage(int page, int count) {
        PageHelper.startPage(page,count);
        List<Article> articleList = articleMapper.getArticByPage(page,count);
        articleList.forEach(article -> {
            Statistic ststisticByArtic = statisticMapper.findStstisticByArticId(article.getId());
            article.setCommentsNum(ststisticByArtic.getCommentsNum());
            article.setHits(ststisticByArtic.getHits());
        });
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public PageInfo<Article> selectArticleByLike(int page, int count, String content) {
        PageHelper.startPage(page,count);
        List<Article> articleList = articleMapper.getArticByLike(page,count,content);
        articleList.forEach(article -> {
            Statistic ststisticByArtic = statisticMapper.findStstisticByArticId(article.getId());
            article.setCommentsNum(ststisticByArtic.getCommentsNum());
            article.setHits(ststisticByArtic.getHits());
        });
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public List<Article> getHeartArticles() {
        List<Integer> hertIdList = statisticMapper.getHertIdList();
        List<Article> articleList = new ArrayList<>();
        for (Integer id : hertIdList) {
            Article article = articleMapper.getArticleById(id);
            articleList.add(article);
        }
        return articleList;
    }



    @Override
    public Article getArticleDetail(int id) {
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        if(article==null){
            article = articleMapper.getArticleById(id);
            Statistic statisticByArticle = statisticMapper.findStstisticByArticId(article.getId());
            article.setCommentsNum(statisticByArticle.getCommentsNum());
            article.setHits(statisticByArticle.getHits());
            redisTemplate.opsForValue().set("article_" + id,article);
        }
        return article;
    }

    @Override
    public List<Article> getArticleByFive() {
        List<Article> articleList = articleMapper.getArticleByFive();
        articleList.forEach(article -> {
            Statistic ststisticByArtic = statisticMapper.findStstisticByArticId(article.getId());
            article.setCommentsNum(ststisticByArtic.getCommentsNum());
            article.setHits(ststisticByArtic.getHits());
        });

        return articleList;

    }

    @Override
    @Transactional
    public void deleteArticleAllInfo(int id) {
        articleMapper.deleteArticle(id);
        commentMapper.deleteComment(id);
        statisticMapper.deleteStatistic(id);
        redisTemplate.delete("article_" + id);
    }
    @Override
    @Transactional
    public void publishArticle(Article article) {
        article.setCreated(new Date());
        articleMapper.insertArticle(article);
        statisticMapper.insertArticleDetail(article.getId());
    }

    @Override
    public void updateArticleWithId(Article article) {
        articleMapper.updateArticle(article);
        redisTemplate.delete("article_" + article.getId());
    }

    @Override
    public void updateCategory(String name, String name1) {

    }

    @Override
    public void updateContentByCid(Article article) {
        if (null != article && null != article.getId()) {
            articleMapper.updateArticle(article);
        }
    }
}
