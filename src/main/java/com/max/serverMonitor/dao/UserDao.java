package com.max.serverMonitor.dao;

import com.max.serverMonitor.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    // 查找用户
    @Select("select * from users where username=#{username} and password=#{password}")
    public User findUser(User user);
    @Select("select salt from users where username=#{username}")
    public String findSalt(String username);
}
