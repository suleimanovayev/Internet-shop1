package mate.academy.internetshop3.model;

import java.util.List;
import mate.academy.internetshop3.generator.OrderIdGenerator;

public class Order {
    private  Long id;
    private  Long userId;
    private  List<Item> items;

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
        id = OrderIdGenerator.getGeneratedId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
