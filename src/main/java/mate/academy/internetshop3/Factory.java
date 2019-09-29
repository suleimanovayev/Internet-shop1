package mate.academy.internetshop3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import mate.academy.internetshop3.dao.BucketDao;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.dao.impl.*;
import mate.academy.internetshop3.dao.impl.BucketDaoImpl;
import mate.academy.internetshop3.dao.impl.OrderDaoImpl;
import mate.academy.internetshop3.dao.impl.jdbc.ItemDaoJdbcImpl;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.OrderService;
import mate.academy.internetshop3.service.UserService;
import mate.academy.internetshop3.service.impl.BucketServiceImpl;
import mate.academy.internetshop3.service.impl.ItemServiceImpl;
import mate.academy.internetshop3.service.impl.OrderServiceImpl;
import mate.academy.internetshop3.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetshop?"
                    + "user=Anna&password=qwe");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Cant establish connection to our DB", e);
        }
    }

    private static ItemDao itemDao;
    private static BucketDao bucketDao;
    private static OrderDao orderDao;
    private static UserDao userDao;

    private static ItemService itemService;
    private static BucketService bucketService;
    private static OrderService orderService;
    private static UserService userService;

    private Factory() {
    }

    public static ItemDao getItemDaoInstance() {
        if (itemDao == null) {
            itemDao = new ItemDaoJdbcImpl(connection);
        }
        return itemDao;
    }

    public static BucketDao getBucketDaoInstance() {
        if (bucketDao == null) {
            bucketDao = new BucketDaoImpl();
        }
        return bucketDao;
    }

    public static OrderDao getOrderDaoInstance() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
        }
        return orderDao;
    }

    public static UserDao getUserDaoInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public static ItemService getItemServiceInstance() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    public static BucketService getBucketServiceInstance() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    public static OrderService getOrderServiceInstance() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    public static UserService getUserServiceInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
}
