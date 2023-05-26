package com.xc.blog_system_xc.controller.admin;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Article;
import com.xc.blog_system_xc.domain.ArticleResponseData;
import com.xc.blog_system_xc.domain.Comment;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminCommentController {
    @Resource
    private CommentService commentService;

    @GetMapping(value = "/comments/toCommentList")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "count", defaultValue = "10") int count,
                        HttpServletRequest request) {
        PageInfo<Comment> pageInfo = commentService.selectCommentByPage(page, count);
        request.setAttribute("comments", pageInfo);
        return "back/comment_list";
    }

    @PostMapping(value = "/comments/delete")
    @ResponseBody
    public ArticleResponseData deleteComment(@RequestParam int id) {
        commentService.deleteCommentById(id);
        return ArticleResponseData.ok(200);
    }
    @PostMapping(value = "/comments/status")
    @ResponseBody
    public ArticleResponseData statusComment(@RequestParam int id) {
        commentService.updateStatus(id);
        return ArticleResponseData.ok(200);
    }
}
