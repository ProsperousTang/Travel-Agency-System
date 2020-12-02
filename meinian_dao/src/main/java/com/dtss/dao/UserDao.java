package com.dtss.dao;

import com.dtss.pojo.User;

public interface UserDao {
    User findUser(String username);
}
