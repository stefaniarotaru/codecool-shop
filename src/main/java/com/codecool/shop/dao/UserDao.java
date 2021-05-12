package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    User get(int id);
    void save(User user);
    void update(User user);
}
