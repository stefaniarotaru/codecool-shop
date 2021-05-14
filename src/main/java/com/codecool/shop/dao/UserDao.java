package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    User getByEmail(String email);
    User save(User user);
    void update(User user);
}
