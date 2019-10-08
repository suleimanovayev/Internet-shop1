package mate.academy.internetshop3.model;

import java.util.List;

public class Order {
    private Long orderId;
    private Long userId;
    private List<Item> items;

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return orderId;
    }

    public void setId(Long id) {
        this.orderId = id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String toString() {
        return "List of product: " + getItems()
                + " number of your order: " + getId()
                + " your number: " + userId;
    }
}
