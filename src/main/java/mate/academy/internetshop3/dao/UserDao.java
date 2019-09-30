package mate.academy.internetshop3.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop3.exceptions.AuthenticationException;
import mate.academy.internetshop3.model.User;

public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    void delete(Long id);

    List<User> getAllUsers();

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
