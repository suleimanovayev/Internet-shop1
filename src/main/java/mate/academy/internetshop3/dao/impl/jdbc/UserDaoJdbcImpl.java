package mate.academy.internetshop3.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.exceptions.AuthenticationException;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Role;
import mate.academy.internetshop3.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static String DB_NAME = "internetshop";
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO " + DB_NAME + ".users (name, surname, login, password, token) "
                + "VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getToken());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long userId = generatedKeys.getLong(1);
                    user.setId(userId);
                }
            }
        } catch (SQLException e) {
            logger.error("Cant create user", e);
        }
        return user;
    }

    @Override
    public User get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surName = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                User user = new User(name, login, password);
                user.setId(id);
                user.setSurName(surName);
                user.setToken(token);
                return user;
            }
        } catch (SQLException e) {
            logger.error("Cant get user", e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET login = ?, password = ?, name = ?, surname = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurName());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            logger.error("Cant update user", e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cant delete user by id", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String surName = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                User user = new User(name, login, password);
                user.setId(userId);
                user.setToken(token);
                user.setSurName(surName);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can't get list of all users", e);
        }
        return userList;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        String query = "SELECT * FROM users WHERE login = ? AND password = ?;";
        Set<Role> roles = new HashSet<>();
        roles.add(Role.of("USER"));
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String surName = resultSet.getString("surname");
                String token = resultSet.getString("token");
                User user = new User(name, login, password);
                user.setId(userId);
                user.setSurName(surName);
                user.setToken(token);
                user.setRole(roles);
                return user;
            }
        } catch (SQLException e) {
            logger.error("Can't get user by login " + login, e);
        }
        throw new AuthenticationException("Cant get user by login = " + login);
    }

    @Override
    public Optional<User> getByToken(String token) {
        String query = "SELECT * FROM users WHERE token = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User user = new User(name, login, password);
                user.setId(id);
                user.setToken(token);
                return Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            logger.error("Can't get user by token", e);
        }
        return Optional.empty();
    }
}
