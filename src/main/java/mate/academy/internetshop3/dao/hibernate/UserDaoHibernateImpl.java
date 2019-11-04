package mate.academy.internetshop3.dao.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.exceptions.AuthenticationException;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Role;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User create(User user) {
        Long userId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            user.setRole(Collections.singleton(Role.of("USER")));
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Cant create User ", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        user.setId(userId);
        return user;
    }

    @Override
    public User get(Long id) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Cant update user ", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        User user = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Cant delete user ", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            list = session.createCriteria(User.class).list();
        }
        return list;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = null;
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new AuthenticationException("incorrect username or password");
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("from User where token =: token");
            query.setParameter("token", token);
            List<User> list = query.list();
            return list.stream().findFirst();
        }
    }
}
