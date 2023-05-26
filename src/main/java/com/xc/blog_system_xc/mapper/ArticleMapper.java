package com.xc.blog_system_xc.mapper;

import com.xc.blog_system_xc.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ArticleMapper {
    /*对于list集合类，用基于注解的sql，较为简单
        对于一些其他类型，使用基于xml的sql,锻炼一下自己基于xml mybatis
        开发能力，之前有曾学过，但是有些忘记了，温故下。

     */

    @Select("SELECT * FROM t_article ORDER BY id DESC")
    List<Article> getArticByPage(int page, int count);


    @Select("SELECT * FROM `t_article` WHERE title LIKE '%${content}%' or content LIKE '%${content}%'")
    List<Article> getArticByLike(int page,int count,String content);


    Article getArticleById(int id);

    @Select("SELECT * FROM t_article ORDER BY id DESC Limit 0,5")
    List<Article> getArticleByFive();

    int getCountArticle();

    void deleteArticle(int articleId);

    @Insert("INSERT INTO t_article (title,created,modified,tags,categories," +
            " allow_comment, thumbnail, content,username)" +
            " VALUES (#{title},#{created}, #{modified}, #{tags}, #{categories}," +
            " #{allowComment}, #{thumbnail}, #{content},#{username})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertArticle(Article article);

    void updateArticle(Article article);

     @Update("")
    void updateByPrimaryKeySelective(Article article);
}
