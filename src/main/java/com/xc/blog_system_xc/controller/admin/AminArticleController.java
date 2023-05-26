package com.xc.blog_system_xc.controller.admin;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.ArticleResponseData;
import com.xc.blog_system_xc.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/admin/article")
public class AminArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping(value = "")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "count", defaultValue = "10") int count,
                        HttpServletRequest request) {
        PageInfo<Article> pageInfo = articleService.selectArtiicByPage(page, count);
        request.setAttribute("articles", pageInfo);
        return "back/article_list";
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public ArticleResponseData deleteArticle(@RequestParam int id) {
        articleService.deleteArticleAllInfo(id);
        return ArticleResponseData.ok(200);
    }

    @PostMapping("/publish")
    @ResponseBody
    public ArticleResponseData publish(Article article) {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!StringUtils.isNotBlank(article.getCategories())) {
            article.setCategories("默认分类");
        }
        try{
            article.setHits(0);
            article.setCommentsNum(0);
            article.setCreated(new Date());
            article.setUsername(user.getUsername());
            articleService.publishArticle(article);
        } catch (Exception e) {
            return ArticleResponseData.fail("发布文章失败，请检查文章内容是否合法");
        }
        return ArticleResponseData.ok("文章发布成");
    }

    @GetMapping(value = "/{id}")
    public String editArticle(@PathVariable("id") String id, HttpServletRequest request) {
        int articleId = Integer.valueOf(id);
        Article article = articleService.getArticleDetail(articleId);
        request.setAttribute("contents", article);
        request.setAttribute("categories", article.getCategories());
        return "back/article_edit";
    }

    @PostMapping(value = "/modify")
    @ResponseBody
    public ArticleResponseData modifyArticle(Article article) {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(article);
        article.setUsername(user.getUsername());
        article.setModified(new Date());
        try {
            articleService.updateArticleWithId(article);
            return ArticleResponseData.ok();
        } catch (Exception e) {
            return ArticleResponseData.fail();
        }
    }

}
