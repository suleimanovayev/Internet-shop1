package mate.academy.internetshop3.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.OrderService;
import mate.academy.internetshop3.service.UserService;

public class CompleteOrderController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> listItem = bucketService.get(userService.get(USER_ID).getBucketId()).getItems();
        req.setAttribute("listItem", listItem);
        orderService.completeOrder(listItem, USER_ID);
        req.getRequestDispatcher("WEB-INF/views/order.jsp").forward(req, resp);
    }
}
