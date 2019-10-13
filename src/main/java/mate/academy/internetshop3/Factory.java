package mate.academy.internetshop3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mate.academy.internetshop3.dao.BucketDao;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.dao.hibernate.ItemDaoHibernateImpl;
import mate.academy.internetshop3.dao.impl.jdbc.BucketDaoJdbcImpl;
import mate.academy.internetshop3.dao.impl.jdbc.OrderDaoJdbcImpl;
import mate.academy.internetshop3.dao.impl.jdbc.UserDaoJdbcImpl;
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
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/internetshop?"
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
            itemDao = new ItemDaoHibernateImpl();
        }
        return itemDao;
    }

    public static BucketDao getBucketDaoInstance() {
        if (bucketDao == null) {
            bucketDao = new BucketDaoJdbcImpl(connection);
        }
        return bucketDao;
    }

    public static OrderDao getOrderDaoInstance() {
        if (orderDao == null) {
            orderDao = new OrderDaoJdbcImpl(connection);
        }
        return orderDao;
    }

    public static UserDao getUserDaoInstance() {
        if (userDao == null) {
            userDao = new UserDaoJdbcImpl(connection);
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
