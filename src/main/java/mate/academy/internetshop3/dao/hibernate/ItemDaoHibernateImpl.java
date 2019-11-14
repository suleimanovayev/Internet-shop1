package mate.academy.internetshop3.dao.hibernate;

import java.util.List;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {

    private static Logger logger = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public Item create(Item item) {
        Long itemId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            itemId = (Long) session.save(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Cant create item " + item, e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        item.setId(itemId);
        return item;
    }

    @Override
    public Item get(Long id) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Item item = session.get(Item.class, id);
            return item;
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Cant update item " + item, e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        Item item = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Cant delete item " + item, e);
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
    public List<Item> getAllItems() {
        List<Item> itemList;
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            itemList = session.createCriteria(Item.class).list();
        }
        return itemList;
    }
}
