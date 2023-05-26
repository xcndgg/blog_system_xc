package com.xc.blog_system_xc.mapper;
import com.xc.blog_system_xc.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CommentMapper {
    /*对于list集合类，用基于注解的sql，较为简单
 对于一些其他类型，使用基于xml的sql,锻炼一下自己基于xml mybatis
 开发能力，之前有曾学过，但是有些忘记了，温故下。

*/
    @Select("SELECT * from t_comment WHERE article_id = #{id}")
    List<Comment> getCommentByArticleId(int id);


    @Select("SELECT * FROM t_comment ORDER BY id DESC limit 0,5")
    List<Comment> getCommentByFive();


    int getCommentCount();


    void deleteComment(int articleId);


    void pushComment(Comment comments);

    @Select("SELECT * FROM t_comment ORDER BY id DESC")
    List<Comment> selectCommentByPage();


    void deleteCommentBy(int id);

    @Select("SELECT * FROM t_comment where id = #{id} ")
    Comment getCommentById(Integer id);

    @Update("UPDATE t_comment set status = #{status} WHERE id = #{id}")
    void updateCommentStatus(String status,Integer id);
}

