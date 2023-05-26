package com.xc.blog_system_xc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.blog_system_xc.domain.Comment;
import com.xc.blog_system_xc.domain.User;
import com.xc.blog_system_xc.mapper.UserMapper;
import com.xc.blog_system_xc.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public void regis(User user) {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userMapper.regis(user);

    }

    @Override
    public PageInfo<User> selectUserByPage(int page, int count) {
        PageHelper.startPage(page,count);
        List<User> list= userMapper.selectUserByPage();
        for (User user : list) {

        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userMapper.deleteUserById(id);
        userMapper.deleteAuthority(id);

    }
}
