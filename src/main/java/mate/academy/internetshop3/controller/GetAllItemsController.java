package mate.academy.internetshop3.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.service.ItemService;

public class GetAllItemsController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> items = itemService.getAllItems();
        req.setAttribute("items", items);
        req.getRequestDispatcher("WEB-INF/views/AllItems.jsp").forward(req, resp);
    }
}