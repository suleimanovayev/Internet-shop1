package mate.academy.internetshop3.service.impl;

import java.util.List;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.lib.Service;
import mate.academy.internetshop3.model.Order;
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
        return userDao.create(user);
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
    public List<Order> getOrders(Long userId) {
        return userDao.get(userId).getOrders();
    }
}
