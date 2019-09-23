package mate.academy.internetshop3.service.impl;

import java.util.List;
import mate.academy.internetshop3.dao.ItemDao;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.lib.Service;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public Item upDate(Item item) {
        return itemDao.update(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }

    @Override
    public void deleteByItem(Item item) {
        itemDao.deleteByItem(item);
    }

    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }
}
