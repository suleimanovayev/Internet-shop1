package mate.academy.internetshop3.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.dao.Storage;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return Storage.orders.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.set(i, order);
                return order;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id) {
        Storage.orders.removeIf(x -> x.getId().equals(id));
    }
}


