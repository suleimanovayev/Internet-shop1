package mate.academy.internetshop3.dao;

import java.util.List;
import mate.academy.internetshop3.model.User;

public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<User> getAllUsers();
}