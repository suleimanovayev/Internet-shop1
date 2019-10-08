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
import mate.academy.internetshop3.service.OrderService;

public class BucketController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Long bucketId = bucketService.getBucketId(userId);
        List<Item> listItem = bucketService.getAllItems(bucketId);
        req.setAttribute("listItem", listItem);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}