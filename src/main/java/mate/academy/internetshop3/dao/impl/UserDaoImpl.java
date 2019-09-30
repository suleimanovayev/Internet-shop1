
package mate.academy.internetshop3.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop3.dao.Storage;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.exceptions.AuthenticationException;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users.stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).equals(user)) {
                Storage.users.set(i, user);
                return user;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id) {
        Storage.users.removeIf(x -> x.getId().equals(id));
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.users;
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        Optional<User> user = Storage.users.stream().filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("incorrect username or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }
}
