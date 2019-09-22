package mate.academy.internetshop3.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop3.generator.UserIdGenerator;

public class User {

    private String name;
    private Long id;
    private Long bucketId;
    private List<Order> orders;

    public User(String name) {
        this.name = name;
        id = UserIdGenerator.getGeneratedId();
        orders = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String toString() {
        return "User name: " + getName()
                + ", User id " + getId();
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }
}

