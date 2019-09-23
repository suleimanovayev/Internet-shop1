package mate.academy.internetshop3.dao;

import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Bucket get(Long bucketId);

    void delete(Long id);

    void deleteItem(Item item);
}