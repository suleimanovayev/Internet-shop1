package mate.academy.internetshop3.dao.hibernate;

import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Order;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public Order create(Order order) {
        Long orderId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        order.setId(orderId);
        return order;
    }

    @Override
    public Order get(Long id) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Order order = session.get(Order.class, id);
            return order;
        }
    }

    @Override
    public Order update(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public void delete(User user) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("DELETE  Order WHERE user_id=:userId");
            query.setParameter("userId", user.getId());
        } catch (Exception e) {
            logger.error("Cant delete order by user");
        }
    }
}
