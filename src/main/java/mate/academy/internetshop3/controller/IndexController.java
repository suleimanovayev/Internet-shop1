package mate.academy.internetshop3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.Item;
import mate.academy.internetshop3.service.ItemService;
import org.apache.log4j.Logger;

public class IndexController extends HttpServlet {
    private static Logger logger = Logger.getLogger(IndexController.class);

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item item = itemService.get(1L);
        logger.info(item);

        Item newItem = new Item();
        newItem.setName("Lenovo");
        newItem.setPrice(1200D);
        itemService.create(newItem);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
