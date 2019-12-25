package com.max.serverMonitor.service;

import com.max.serverMonitor.dao.UserDao;
import com.max.serverMonitor.domain.User;
import com.max.serverMonitor.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findUser(User user) {
        return userDao.findUser(user);
    }

    public String findSalt(String username) {
        return userDao.findSalt(username);
    }


}
