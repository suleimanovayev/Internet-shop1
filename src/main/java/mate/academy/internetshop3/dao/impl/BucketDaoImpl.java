package mate.academy.internetshop3.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop3.dao.BucketDao;
import mate.academy.internetshop3.dao.Storage;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getId().equals(bucket.getId())) {
                return Storage.buckets.set(i, bucket);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets.stream()
                .filter(x -> x.getId().equals(bucketId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void delete(Long id) {
        Storage.buckets.removeIf(x -> x.getId().equals(id));
    }

    @Override
    public Bucket deleteItem(Long bucketId, Long itemId) {
        return null;
    }

    @Override
    public Boolean addItem(Long itemId, Long bucketId) {
        return null;
    }

    @Override
    public Long getBucketId(Long userId) {
        return null;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return null;
    }
}
