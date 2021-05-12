package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class UserDaoJdbc implements UserDao {

    private DataSource dataSource = DatabaseManager.getInstance().getDataSource();

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }
}
