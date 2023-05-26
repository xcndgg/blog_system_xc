package com.xc.blog_system_xc.controller.client;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.Statistic;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private ArticleService articleService;
    @Resource
    private StatisticService statisticService;


    @GetMapping("/")
    public String toIndex(HttpServletRequest request) {
        return page(request,1,5);
    }

    //分页查询文章
    @RequestMapping(value = "/page/{pageCount}",method = RequestMethod.GET)
    public String page(HttpServletRequest request,
                        @PathVariable("pageCount") int page,
                        @RequestParam(value = "count",defaultValue = "5")int count) {
        PageInfo<Article> articlePageInfos = articleService.selectArtiicByPage(page,count);
        List<Article> articleHeartList = articleService.getHeartArticles();
        articleHeartList.forEach(article -> {
            Statistic statistic = statisticService.findStstisticByArticId(article.getId());
            article.setHits(statistic.getHits());
            article.setCommentsNum(statistic.getCommentsNum());
        });
        request.setAttribute("articles",articlePageInfos);
        request.setAttribute("articleList",articleHeartList);
        return "/client/index";
    }

    //模糊查询文章实现搜索功能
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public String pageByLike(HttpServletRequest request, @RequestParam(value = "input") String content) {
        PageInfo<Article> articlePageInfos = articleService.selectArticleByLike(1,5,content);
        List<Article> articleHeartList = articleService.getHeartArticles();
        request.setAttribute("articles",articlePageInfos);
        request.setAttribute("articleList",articleHeartList);
        return "/client/index";
    }
}
