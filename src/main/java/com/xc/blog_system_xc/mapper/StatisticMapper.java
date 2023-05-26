package com.xc.blog_system_xc.mapper;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.Statistic;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface StatisticMapper {

       /*对于list集合类，用基于注解的sql，较为简单
        对于一些其他类型，使用基于xml的sql,锻炼一下自己基于xml mybatis
        开发能力，之前有曾学过，但是有些忘记了，温故下。

     */
    Statistic findStstisticByArticId(Integer articleId);


    @Select("SELECT t_statistic.article_id FROM t_statistic ORDER BY t_statistic.hits DESC limit 0 ,9")
    List<Integer> getHertIdList();


    void updateArticleInfoByArticle(int hits,int id);

    void deleteStatistic(int articleId);

    void addCommentCount(int count, Integer id);

    @Insert("INSERT INTO t_statistic(article_id,hits,comments_num) values (#{id},'0','0')")
    void insertArticleDetail(Integer id);
}

