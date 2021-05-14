package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.exception.NotFoundException;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private static final String SELECT_USER_STATEMENT = "select * from \"user\" where email = ?;";
    private static final String INSERT_USER_STATEMENT = "insert into \"user\" (name, password, email, phone) " +
            "values (?, ?, ?, ?);";

    private static final String USER_NOT_FOUND = "User with email %s does not exist.";

    private DataSource dataSource = DatabaseManager.getInstance().getDataSource();

    @Override
    public User getByEmail(String email) {
      try (Connection connection =  dataSource.getConnection()) {
          PreparedStatement st = connection.prepareStatement(SELECT_USER_STATEMENT);
          st.setString(1, email);
          ResultSet rs = st.executeQuery();

          if (!rs.next()) {
              throw new NotFoundException(String.format(USER_NOT_FOUND, email));
          }

          return new User(
                  rs.getInt("id"),
                  rs.getString("name"),
                  rs.getString("email"),
                  rs.getString("phone"),
                  rs.getString("password")
          );
      } catch (SQLException e) {
            throw new RuntimeException(e);
      }
    }

    @Override
    public User save(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement st = connection.prepareStatement(INSERT_USER_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getName());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPhone());

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {

    }
}
