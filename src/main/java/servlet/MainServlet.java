package servlet;

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
 * The class describe servlet what serve load main.jsp page.
 */
public class MainServlet extends HttpServlet {
    /**
     * The method load all item to attribute from db.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allItems", Hibernate.instOf().getAllItems());
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}