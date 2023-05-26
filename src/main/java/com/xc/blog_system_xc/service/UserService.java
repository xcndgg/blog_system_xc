package com.xc.blog_system_xc.service;

import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Comment;
import com.xc.blog_system_xc.domain.User;

public interface UserService {

    public void regis(User user);

    PageInfo<User> selectUserByPage(int page, int count);

    void deleteUserById(int id);
}
