package mate.academy.internetshop3.service.impl;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.lib.Service;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.model.Order;
import mate.academy.internetshop3.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;

    @Inject
    private static UserDao userDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order upDate(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order completeOrder(List items, Long userId) {
        List<Item> newItems = new ArrayList<>(items);
        Order order = new Order(userId, newItems);
        orderDao.create(order);
        return order;
    }

    @Override
    public List<Order> getAllOrdersForUser(Long userId) {
        return getAllOrdersForUser(userId);
    }
}
