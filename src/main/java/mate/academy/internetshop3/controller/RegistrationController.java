package mate.academy.internetshop3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.Bucket;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.service.BucketService;
import mate.academy.internetshop3.service.UserService;

public class RegistrationController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("user.name");
        String login = req.getParameter("login");
        String psw = req.getParameter("psw");
        User newUser = new User(name, login, psw);
        User user = userService.create(newUser);

        Bucket bucket = new Bucket(user.getId());
        bucketService.create(bucket);

        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);

        HttpSession session = req.getSession(true);
        session.setAttribute("user.id", user.getId());
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
