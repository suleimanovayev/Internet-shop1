package mate.academy.internetshop3.service;

import java.util.List;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;

public interface BucketService {

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    void delete(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket deleteItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);

    Bucket getBucketByUserId(Long userId);
}
