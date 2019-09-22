package mate.academy.internetshop3.model;

import java.util.List;
import mate.academy.internetshop3.generator.OrderIdGenerator;

public class Order {
    private final Long id;
    private final Long userId;
    private final List<Item> items;

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
        id = OrderIdGenerator.getGeneratedId();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public String toString() {
        return "List of product: " + getItems()
                + " number of your order: " + getId()
                + " your number: " + userId;
    }
}

