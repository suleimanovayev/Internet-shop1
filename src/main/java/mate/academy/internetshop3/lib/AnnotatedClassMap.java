package mate.academy.internetshop3.lib;

import java.util.HashMap;
import java.util.Map;
import mate.academy.internetshop3.Factory;
import mate.academy.internetshop3.dao.BucketDao;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.OrderService;
import mate.academy.internetshop3.service.UserService;

public class AnnotatedClassMap {
    private static Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(ItemDao.class, Factory.getItemDaoInstance());
        classMap.put(ItemService.class, Factory.getItemServiceInstance());
        classMap.put(BucketDao.class, Factory.getBucketDaoInstance());
        classMap.put(BucketService.class, Factory.getBucketServiceInstance());
        classMap.put(OrderDao.class, Factory.getOrderDaoInstance());
        classMap.put(OrderService.class, Factory.getOrderServiceInstance());
        classMap.put(UserDao.class, Factory.getUserDaoInstance());
        classMap.put(UserService.class, Factory.getUserServiceInstance());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}

