package mate.academy.internetshop3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.UserService;

public class InjectData extends HttpServlet {

    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User anna = new User("Anna");
        userService.create(anna);

        Bucket bucket = new Bucket(anna.getId());
        bucketService.create(bucket);

        Item item1 = new Item("Samsung s10", 27000.0);
        itemService.create(item1);
        Item item2 = new Item("Huawei Up8", 12222.0);
        itemService.create(item2);
        Item item3 = new Item("Xiaomi M19", 14562.0);
        itemService.create(item3);
        Item item4 = new Item("Bravis A511", 14262.0);
        itemService.create(item4);
        Item item5 = new Item("Iphone Xr", 29000.0);
        itemService.create(item5);
        Item item6 = new Item("Samsung A50", 23245.0);
        itemService.create(item6);
        Item item7 = new Item("Huawei W8", 13999.0);
        itemService.create(item7);
    }
}
