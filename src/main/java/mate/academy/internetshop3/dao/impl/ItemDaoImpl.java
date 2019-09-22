package mate.academy.internetshop3.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.dao.Storage;
import mate.academy.internetshop3.lib.Dao;
import mate.academy.internetshop3.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.set(i, item);
                return item;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id) {
        Storage.items.removeIf(n -> n.getId().equals(id));
    }

    @Override
    public void deleteByItem(Item item) {
        Storage.items.removeIf(item::equals);
    }

    public List<Item> getAllItems() {
        return Storage.items;
    }
}


