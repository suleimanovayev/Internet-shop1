package mate.academy.internetshop3.dao.hibernate;

import java.util.List;
import mate.academy.internetshop3.dao.BucketDao;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        Long bucketId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
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
        bucket.setId(bucketId);
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
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
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Bucket bucket = session.get(Bucket.class, bucketId);
            return bucket;
        }
    }

    @Override
    public void delete(Long id) {
        Bucket bucket = get(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(bucket);
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
    }

    @Override
    public Bucket getBucketByUserId(Long userId) {
        Bucket bucket = null;
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("FROM Bucket WHERE user_id =:userId");
            query.setParameter("userId", userId);
            bucket = (Bucket) query.uniqueResult();
        } catch (Exception e) {
            logger.error("cant get bucket", e);
        }
        return bucket;
    }

    @Override
    public Boolean addItem(Long itemId, Long bucketId) {
        try {
            Bucket bucket = get(bucketId);
            List<Item> list = bucket.getItems();
            Item item = itemDao.get(itemId);
            list.add(item);
            update(bucket);
            return true;
        } catch (Exception e) {
            logger.error("Can't add item to bucket ", e);
            return false;
        }
    }

    @Override
    public Bucket deleteItem(Long bucketId, Long itemId) {
        Bucket bucket = null;
        try {
            bucket = get(bucketId);
            List<Item> list = bucket.getItems();
            Item item = itemDao.get(itemId);
            for (Item item1 : list) {
                if (item.getId().equals(item1.getId())) {
                    list.remove(item1);
                    break;
                }
            }
            update(bucket);
        } catch (Exception e) {
            logger.error("Can't delete item from bucket ", e);
        }
        return bucket;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        List<Item> itemList = get(bucketId).getItems();
        return itemList;
    }
}
