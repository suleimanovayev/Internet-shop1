package mate.academy.internetshop3.model;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop3.generator.UserIdGenerator;

public class User {

    private String name;
    private String surName;
    private String login;
    private String password;
    private String token;
    private Long id;
    private Long bucketId;
    private List<Order> orders;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
