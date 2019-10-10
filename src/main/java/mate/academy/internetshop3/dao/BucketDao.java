package mate.academy.internetshop3.dao;

import java.util.List;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Bucket get(Long bucketId);

    void delete(Long id);

    Long getBucketId(Long userId);

    Boolean addItem(Long itemId, Long bucketId);

    Bucket deleteItem(Long bucketId, Long itemId);

    List<Item> getAllItems(Long bucketId);
}
