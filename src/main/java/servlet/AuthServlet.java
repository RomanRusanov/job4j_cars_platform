package servlet;

import filter.app.BrandAudi;
import filter.FilterOperation;
import filter.app.ThisDay;
import filter.app.WithPhoto;
import model.Image;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import store.Hibernate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 21.01.2021
 * email roman9628@gmail.com
 * The class describe servlet that authenticate users.
 */
public class AuthServlet extends HttpServlet {
    /**
     * The instance with logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AuthServlet.class.getName());
    /**
     * The marker for logger.
     */
    private static final Marker MARKER = MarkerFactory.getMarker("Auth");
    /**
     * The servlet get request from DB if user with passed name and password
     * exist in db redirect to main.do otherwise to reg.jsp page.
     * When user pass authentication add session attribute "imagesAdd" where store images
     * before item is store to db on add_item.jsp page. Attribute "filterOperation" store
     * all filters what user can use.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User userFromDB = Hibernate.instOf().findUserByLogin(login);
        if (!userFromDB.getName().equals("-1")
                && userFromDB.getName().equals(login)
                && userFromDB.getPassword().equals(password))
        {
            LOG.info(MARKER, "Server authenticate user: {}", userFromDB);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", userFromDB);
            sc.setAttribute("imagesAdd", new ArrayList<Image>());
            sc.setAttribute("filterOperation", this.initFilters());
            resp.sendRedirect(req.getContextPath() + "/main.do");
        } else {
            LOG.info(MARKER, "Try to login not registered user.");
            resp.sendRedirect(req.getContextPath() + "/reg.jsp");
        }
    }

    /**
     * The method add all filters to application.
     * @return instance for filter interaction.
     */
    private FilterOperation initFilters() {
        FilterOperation filterOperation = new FilterOperation();
        filterOperation.addFilter(new ThisDay());
        filterOperation.addFilter(new WithPhoto());
        filterOperation.addFilter(new BrandAudi());
        return filterOperation;
    }
}