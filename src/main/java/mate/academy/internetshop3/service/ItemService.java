package mate.academy.internetshop3.service;

import java.util.List;
import mate.academy.internetshop3.model.Item;

public interface ItemService {

    Item create(Item item);

    Item get(Long id);

    Item upDate(Item item);

    void delete(Long id);

    void deleteByItem(Item item);

    List<Item> getAllItems();
}
