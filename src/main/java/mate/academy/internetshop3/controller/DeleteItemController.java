package mate.academy.internetshop3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.ItemService;
import mate.academy.internetshop3.service.UserService;

public class DeleteItemController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    private static final Long USER_ID = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item.id");
        bucketService.deleteItem(userService.get(USER_ID).getId(), Long.parseLong(itemId));
        resp.sendRedirect(req.getContextPath() + "/bucket");
    }
}
