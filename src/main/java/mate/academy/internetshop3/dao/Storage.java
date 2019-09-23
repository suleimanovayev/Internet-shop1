package mate.academy.internetshop3.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.model.Order;
import mate.academy.internetshop3.model.User;

public class Storage {
    public static final List<Item> items = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
}
