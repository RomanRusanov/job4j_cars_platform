package servlet;

import model.User;
import store.Hibernate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 21.01.2021
 * email roman9628@gmail.com
 * The class describe servlet what serve user registration page.
 */
public class RegServlet extends HttpServlet{
    /**
     * The method redirect to reg.jsp.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/reg.jsp").forward(req, resp);
    }
    /**
     * The method store to db new user with passed name and password,
     * and then redirect to login.jsp page.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Hibernate.instOf().persistModel(
                User.of(
                        req.getParameter("login"),
                        req.getParameter("password")
                )
        );
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

}