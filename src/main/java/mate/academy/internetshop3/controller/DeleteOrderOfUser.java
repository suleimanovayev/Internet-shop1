package mate.academy.internetshop3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.OrderService;

public class DeleteOrderOfUser extends HttpServlet {

    @Inject
    private static OrderService orderService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        orderService.delete(USER_ID);
        bucketService.clear(bucketService.get(USER_ID).getId());
        req.getRequestDispatcher("WEB-INF/views/order.jsp").forward(req, resp);
    }
}
