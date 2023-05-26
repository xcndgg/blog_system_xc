package com.xc.blog_system_xc.controller.admin;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.ArticleResponseData;
import com.xc.blog_system_xc.domain.Comment;
import com.xc.blog_system_xc.domain.User;
import com.xc.blog_system_xc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userSystem")
public class UserSystemController {
    @Resource
    private UserService userService;
    @GetMapping(value = "/toUserList")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "count", defaultValue = "10") int count,
                        HttpServletRequest request) {
        PageInfo<User> pageInfo = userService.selectUserByPage(page, count);
        request.setAttribute("users", pageInfo);
        return "back/user_list";
    }

    @PostMapping(value = "/deleteUser")
    @ResponseBody
    public ArticleResponseData deleteUser(@RequestParam int id) {
        userService.deleteUserById(id);
        return ArticleResponseData.ok(200);
    }
}
