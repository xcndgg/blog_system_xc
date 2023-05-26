package com.xc.blog_system_xc.controller.admin;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.ArticleResponseData;
import com.xc.blog_system_xc.domain.TagAndCategory;
import com.xc.blog_system_xc.domain.User;
import com.xc.blog_system_xc.service.CategoryService;
import com.xc.blog_system_xc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping(value = "/categoryList")
    public String index(HttpServletRequest request) {
        List<TagAndCategory> list = new ArrayList<>();
        List<TagAndCategory> list2 = new ArrayList<>();
        list = categoryService.TagAndCategoryList("tag");
        list2 = categoryService.TagAndCategoryList("category");

        request.setAttribute("categories",list2);
        request.setAttribute("tags", list);
        return "back/category_list";
    }
    @PostMapping(value = "/save")
    @ResponseBody
    @Transactional()
    public ArticleResponseData saveCategory(@RequestParam String cname, @RequestParam(value = "mid", defaultValue = "" ) Integer mid) {
        try {
            categoryService.saveMeta("category",cname,mid);
        } catch (Exception e) {
            String msg = "分类保存失败";
            return ArticleResponseData.fail("分类保存失败");
        }

        return ArticleResponseData.ok(200);
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    @Transactional
    public ArticleResponseData delete(@RequestParam int mid) {
        try {
            categoryService.delete(mid);
        } catch (Exception e) {

            return ArticleResponseData.fail("分类删除失败");
            }

        return ArticleResponseData.ok();
    }
}
