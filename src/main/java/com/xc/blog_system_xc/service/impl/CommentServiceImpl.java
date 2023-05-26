package com.xc.blog_system_xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.Comment;
import com.xc.blog_system_xc.mapper.ArticleMapper;
import com.xc.blog_system_xc.mapper.CommentMapper;
import com.xc.blog_system_xc.mapper.StatisticMapper;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.CommentService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private StatisticMapper statisticMapper;
    @Resource
    private ArticleService articleService;
    @Resource
    private ArticleMapper articleMapper;
    @Override
    public PageInfo<Comment> getCommentByArticleId(int id, int page, int count) {
        PageHelper .startPage(page,count);
        List<Comment> commentInfos = commentMapper.getCommentByArticleId(id);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentInfos);
        return pageInfo;
    }

    @Override
    public List<Comment> getCommentByFive() {
        return commentMapper.getCommentByFive();
    }

    @Override
    public void pushComment(Comment comments) {
        commentMapper.pushComment(comments);
        Article article = articleService.getArticleDetail(comments.getArticleId());
        statisticMapper.addCommentCount(article.getCommentsNum()+1,article.getId());

    }

    @Override
    public PageInfo<Comment> selectCommentByPage(int page, int count) {
        PageHelper.startPage(page,count);
        List<Comment> comments = commentMapper.selectCommentByPage();
        comments.forEach(comment -> {
            Article articleDetail = articleMapper.getArticleById(comment.getArticleId());
            comment.setArticleName(articleDetail.getTitle());
        });
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return pageInfo;
    }

    @Override
    public void deleteCommentById(int id) {
         commentMapper.deleteCommentBy(id);
    }

    @Override
    public void updateStatus(int id) {
        Comment comment = commentMapper.getCommentById(id);
        if(comment.getStatus().equals("approved")) {
            comment.setStatus("not_audit");
        } else {
            comment.setStatus("approved");
        }
        commentMapper.updateCommentStatus(comment.getStatus(),comment.getId());
    }
}