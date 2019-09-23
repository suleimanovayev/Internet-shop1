package mate.academy.internetshop3.dao;

import java.util.List;
import mate.academy.internetshop3.model.Item;

public interface ItemDao {

    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    void delete(Long id);

    void deleteByItem(Item item);

    List<Item> getAllItems();
}