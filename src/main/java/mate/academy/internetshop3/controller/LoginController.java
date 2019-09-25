package mate.academy.internetshop3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop3.exceptions.AuthenticationException;
import mate.academy.internetshop3.lib.Inject;
import mate.academy.internetshop3.model.User;
import mate.academy.internetshop3.service.UserService;
import org.apache.log4j.Logger;

public class LoginController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("psw");
        try {
            User user = userService.login(login, password);

            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.getId());

            Cookie cookie = new Cookie("MATE", user.getToken());
            resp.addCookie(cookie);

            resp.sendRedirect(req.getContextPath() + "/servlet/index");
        } catch (AuthenticationException e) {
            req.setAttribute("error", "incorrect login or password");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
