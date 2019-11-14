package mate.academy.internetshop3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.Role;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.OrderService;
import mate.academy.internetshop3.service.UserService;

public class InjectData extends HttpServlet {

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = new User("User","0","0");
        user.addRole(Role.of("USER"));
        userService.create(user);

        User admin = new User("Admin","1","1");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);

        Bucket bucket = new Bucket(user);
        bucketService.create(bucket);

        user.setBucket(bucket);
    }
}
