package mate.academy.internetshop3.service.impl;

import java.util.List;
import mate.academy.internetshop3.dao.BucketDao;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.dao.OrderDao;
import mate.academy.internetshop3.dao.UserDao;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.lib.Service;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Inject
    private static OrderDao orderDao;

    @Inject
    private static UserDao userDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {
        bucketDao.delete(id);
    }

    @Override
    public Bucket addItem(Long itemId, Long bucketId) {
        bucketDao.addItem(itemId, bucketId);
        return update(bucketDao.get(bucketId));
    }

    @Override
    public Bucket deleteItem(Long bucketId, Long itemId) {
        bucketDao.deleteItem(bucketId, itemId);
        return update(bucketDao.get(bucketId));
    }

    @Override
    public Bucket clear(Long bucketId) {
        bucketDao.get(bucketId).getItems().clear();
        return bucketDao.get(bucketId);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return bucketDao.getAllItems(bucketId);
    }

    @Override
    public Bucket getBucketByUserId(Long userId) {
        return bucketDao.getBucketByUserId(userId);
    }
}
