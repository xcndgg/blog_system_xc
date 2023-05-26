package com.xc.blog_system_xc.service;

import com.xc.blog_system_xc.domain.TagAndCategory;

import java.util.List;

public interface CategoryService {
    List<TagAndCategory> TagAndCategoryList(String tag);

    void saveMeta(String category, String cname, Integer mid) throws Exception;

    void delete(int mid);
}
