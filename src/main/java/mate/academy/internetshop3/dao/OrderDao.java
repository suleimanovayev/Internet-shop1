package mate.academy.internetshop3.dao;

import mate.academy.internetshop3.model.Order;

public interface OrderDao {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    void delete(Long id);
}
