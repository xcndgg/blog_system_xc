package com.xc.blog_system_xc.controller.client;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.Comment;
import com.xc.blog_system_xc.domain.Statistic;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.CommentService;
import com.xc.blog_system_xc.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/*
文章
 */
@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;
    @Resource
    private StatisticService statisticService;

    @GetMapping("/article/{id}")
    public String findArticleDetail(HttpServletRequest httpServletRequest,@PathVariable int id) {
        String cp = httpServletRequest.getParameter("cp");
        Article article = articleService.getArticleDetail(id);
        Statistic statistic = statisticService.findStstisticByArticId(id);
        if(article!=null) {
            if(cp == null) {
                cp = "1";
            } else{
                cp = cp;
            }
            int page = Integer.valueOf(cp);
            PageInfo<Comment> commentByArticleId = commentService.getCommentByArticleId(id, page, 5);
            httpServletRequest.setAttribute("cp", cp);
            httpServletRequest.setAttribute("comments", commentByArticleId);
            statisticService.updateArticleInfoByArticle(statistic);
            httpServletRequest.setAttribute("article",article);
            return "client/articleDetails";
        } else {
           return "comm/error_404";
        }

    }
}
