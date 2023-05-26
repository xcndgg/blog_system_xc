package com.xc.blog_system_xc;

import com.github.pagehelper.PageHelper;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.User;
import com.xc.blog_system_xc.mapper.ArticleMapper;
import com.xc.blog_system_xc.mapper.StatisticMapper;
import com.xc.blog_system_xc.mapper.UserMapper;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.CommentService;
import com.xc.blog_system_xc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogSystemXcApplicationTests {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private StatisticMapper statisticMapper;
    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void getArPage() {
//        PageHelper.startPage(1,5);
//        List<Article> articByPage = articleMapper.getArticByPage(1, 5);
//        for (Article article : articByPage) {
//            System.out.println(articByPage);
//        }
//        System.out.println(statisticMapper.findStstisticByArticId(1));
//        List<Article> heartArticles = articleService.getHeartArticles();
//        heartArticles.forEach(article -> {
//            System.out.println(article);
//        });

//        System.out.println(commentService.selectCommentByPage(1,10));
//        User user = new User();
//        user.setPassword("123456xc");
//        user.setUsername("xc");
//        user.setEmail("gdgf");
//        userService.regis(user);
        System.out.println(userService.selectUserByPage(1,5));

    }

}
