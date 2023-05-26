package com.xc.blog_system_xc.service;

import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.StaticticsBo;
import com.xc.blog_system_xc.domain.Statistic;

public interface StatisticService {


    Statistic findStstisticByArticId(Integer articleId);


    void updateArticleInfoByArticle(Statistic statistic);

    StaticticsBo getStaticBo();
}
