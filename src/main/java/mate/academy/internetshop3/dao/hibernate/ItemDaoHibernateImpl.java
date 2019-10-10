package mate.academy.internetshop3.dao.hibernate;

import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public Item create(Item item) {
        Long itemIt = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            try {
                itemIt = (Long) session.save(item);
            } catch (Exception e) {
                session.close();
                throw e;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        item.setId(itemIt);
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
            if (transaction != null) {
                transaction.rollback();
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
            if (transaction != null) {
                transaction.rollback();
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
