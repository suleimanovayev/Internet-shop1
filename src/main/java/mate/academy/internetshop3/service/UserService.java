package mate.academy.internetshop3.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop3.exceptions.AuthenticationException;
import mate.academy.internetshop3.model.User;

public interface UserService {

    List<User> getAllUsers();

    User create(User user);

    User get(Long id);

    User upDate(User user);

    void delete(Long id);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
