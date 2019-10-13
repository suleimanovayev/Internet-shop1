package mate.academy.internetshop3.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.exceptions.AuthenticationException;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.lib.Service;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User create(User user) {
        user.setToken(getToken());
        return userDao.create(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User upDate(User user) {
        return userDao.update(user);
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return userDao.login(login, password);
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }
}
