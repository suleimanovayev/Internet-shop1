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

public class AddItemController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");

        String itemId = req.getParameter("item.id");
        bucketService.addItem(userService.get(userId).getBucketId(), Long.parseLong(itemId));

        resp.sendRedirect(req.getContextPath() + "/servlet/GetAllItems");
    }
}