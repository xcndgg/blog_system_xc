package com.xc.blog_system_xc.service.impl;

import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.StaticticsBo;
import com.xc.blog_system_xc.domain.Statistic;
import com.xc.blog_system_xc.mapper.ArticleMapper;
import com.xc.blog_system_xc.mapper.StatisticMapper;
import com.xc.blog_system_xc.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Resource
    private StatisticMapper statisticMapper;

    @Override
    public Statistic findStstisticByArticId(Integer articleId) {
        return statisticMapper.findStstisticByArticId(articleId);
    }

    @Override
    public void updateArticleInfoByArticle(Statistic statistic) {
        statisticMapper.updateArticleInfoByArticle(statistic.getHits()+1,statistic.getArticleId());
    }

    @Override
    public StaticticsBo getStaticBo() {
        return null;
    }
}
