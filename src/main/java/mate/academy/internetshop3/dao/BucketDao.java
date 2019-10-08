package mate.academy.internetshop3.dao;

import java.util.List;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Bucket get(Long bucketId);

    void delete(Long id);

    Bucket deleteItem(Long bucketId, Long itemId);

    Boolean addItem(Long itemId, Long bucketId);

    Long getBucketId(Long userId);

    List<Item> getAllItems(Long bucketId);
}
