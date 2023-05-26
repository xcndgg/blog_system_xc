package com.xc.blog_system_xc.controller.admin;

import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.Comment;
import com.xc.blog_system_xc.domain.StaticticsBo;
import com.xc.blog_system_xc.mapper.ArticleMapper;
import com.xc.blog_system_xc.mapper.CommentMapper;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.CommentService;
import com.xc.blog_system_xc.service.StatisticService;
import com.xc.blog_system_xc.service.impl.CommentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;
    @Resource
    private StatisticService statisticService;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private CommentMapper commentMapper;

    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest httpServletRequest) {
        List<Article> articleByFive = articleService.getArticleByFive();
        List<Comment> commentList = commentService.getCommentByFive();
        StaticticsBo staticticsBo = new StaticticsBo();
        staticticsBo.setArticles(articleMapper.getCountArticle());
        staticticsBo.setComments(commentMapper.getCommentCount());
        httpServletRequest.setAttribute("comments", commentList);
        httpServletRequest.setAttribute("articles", articleByFive);
        httpServletRequest.setAttribute("statistics", staticticsBo);
        return "back/index";

    }

    @GetMapping(value = "/article/toEditPage")
    public String putArticle( ) {
        return "back/article_edit";
    }


}
