package mate.academy.internetshop3.service;

import java.util.List;
import mate.academy.internetshop3.model.Order;

public interface OrderService {

    Order create(Order order);

    Order get(Long id);

    Order upDate(Order order);

    void delete(Long id);

    Order completeOrder(List items, Long userId);

    List<Order> getAllOrdersForUser(Long userId);
}

