package com.xc.blog_system_xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.Comment;

import java.util.List;

public interface CommentService {
    PageInfo<Comment> getCommentByArticleId(int id, int page, int count);

    List<Comment> getCommentByFive();

    void pushComment(Comment comments);

    PageInfo<Comment> selectCommentByPage(int page, int count);

    void deleteCommentById(int id);

    void updateStatus(int id);
}
