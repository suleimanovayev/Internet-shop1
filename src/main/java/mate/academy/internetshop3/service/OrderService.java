package mate.academy.internetshop3.service;

import java.util.List;
import mate.academy.internetshop3.model.Order;
import mate.academy.internetshop3.model.User;

public interface OrderService {

    Order create(Order order);

    Order get(Long id);

    Order upDate(Order order);

    void delete(User user);

    Order completeOrder(List items, Long userId);

    List<Order> getAllOrdersForUser(Long userId);
}
