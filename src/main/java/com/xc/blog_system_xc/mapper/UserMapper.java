package com.xc.blog_system_xc.mapper;

import com.xc.blog_system_xc.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO t_user(username,password,email) VALUES(#{username},#{password},#{email})")
    public void regis(User user);

    @Select("SELECT t_user.*,t_authority.authority from t_user ,t_authority WHERE t_user.id = t_authority.id")
    List<User> selectUserByPage();

    @Delete("DELETE FROM t_user WHERE id=#{id}")
    void deleteUserById(int id);

    @Delete("DELETE FROM t_authority WHERE id=#{id}")
    void deleteAuthority(int id);
}
