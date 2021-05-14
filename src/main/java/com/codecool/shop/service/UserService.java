package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User;

public class UserService {

    private final UserDao userDao = new UserDaoJdbc();

    public User register(String name, String email, String phone, String password) {
        User newUser = new User(name, email, phone, password);
        return userDao.save(newUser);
    }
}
