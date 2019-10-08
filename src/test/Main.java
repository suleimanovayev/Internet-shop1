package mate.academy.internetshop3;

import mate.academy.internetshop3.dao.Storage;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.lib.Injector;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.OrderService;
import mate.academy.internetshop3.service.UserService;

public class Main {

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    public static void main(String[] args) {
        Item headPhones = itemService.create(new Item("Airpods",200.0));
        Item phone = itemService.create(new Item("Samsung S8",100.0));
        Item speaker = itemService.create(new Item("JBL",200.0));
        System.out.println(Storage.items);

        itemService.deleteByItem(phone);
        System.out.println(Storage.items);

        User user1 = new User("Yevhenia");
        userService.create(user1);

        Bucket bucket = new Bucket(user1.getId());
        bucketService.create(bucket);
        bucketService.addItem(bucket.getId(), headPhones.getId());
        bucketService.addItem(bucket.getId(), speaker.getId());

        orderService.completeOrder(bucket.getItems(), user1.getId());
        System.out.println(Storage.orders);
    }
}