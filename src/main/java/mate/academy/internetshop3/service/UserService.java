package mate.academy.internetshop3.service;

import java.util.List;
import mate.academy.internetshop3.model.User;

public interface UserService {

    List<User> getAllUsers();

    User create(User user);

    User get(Long id);

    User upDate(User user);

    List getOrders(Long userId);
}

