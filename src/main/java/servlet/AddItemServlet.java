package servlet;

import model.Car;
import model.Category;
import model.Image;
import model.Item;
import model.User;
import model.carType.BodyType;
import model.carType.Brand;
import model.carType.EngineType;
import model.carType.Transmission;
import model.carType.WheelDriveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import store.Hibernate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 22.01.2021
 * email roman9628@gmail.com
 * The class describe servlet that serves page add_item.jsp.
 */
public class AddItemServlet extends HttpServlet {
    /**
     * The instance with logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddItemServlet.class.getName());
    /**
     * The marker for logger.
     */
    private static final Marker MARKER = MarkerFactory.getMarker("Auth");
    /**
     * The metho fill table on main.do page.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("all_brands", Hibernate.instOf().getAllBrands());
        req.setAttribute("all_transmission_types", Hibernate.instOf().getAllTransmissionTypes());
        req.setAttribute("all_body_types", Hibernate.instOf().getAllBodyTypes());
        req.setAttribute("all_engine_types", Hibernate.instOf().getAllEngineTypes());
        req.setAttribute("all_wheel_drive_types", Hibernate.instOf().getAllWheelDriveTypes());
        req.setAttribute("all_categories", Hibernate.instOf().getAllCategories());
        ArrayList<Image> list = (ArrayList<Image>) req.getSession().getAttribute("imagesAdd");
        req.setAttribute("list_size", list.size());
        req.getRequestDispatcher("/add_item.jsp").forward(req, resp);
    }
    /**
     * The post add new item to db and return to main.jsp page.
     * Reinitialize session attribute imagesAdd, with new ArrayList
     * (The ability for the user to add new images).
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User currentUser = (User) req.getSession().getAttribute("user");
        Long brand_id = Long.valueOf(req.getParameter("brand"));
        String model = req.getParameter("model");
        Integer price = Integer.valueOf(req.getParameter("price"));
        String creation_date = req.getParameter("creation_date");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(creation_date);
        } catch (ParseException e) {
            LOG.error(MARKER, "Can't parse date from add_item.jsp ", e);
        }
        Long transmission_id = Long.valueOf(req.getParameter("transmission"));
        Long body_type_id = Long.valueOf(req.getParameter("body_type"));
        Long engine_type_id = Long.valueOf(req.getParameter("engine_type"));
        Integer mileage = Integer.valueOf(req.getParameter("mileage"));
        Long wheelDriveType_id = Long.valueOf(req.getParameter("wheelDriveType"));
        String description = req.getParameter("description");
        String[] category_ids = req.getParameterValues("category");
        Item item = Item.of(
                description,
                false,
                currentUser,
                Car.of(Hibernate.instOf().getInstanceById(Brand.class, brand_id),
                        model,
                        price,
                        date,
                        Hibernate.instOf().getInstanceById(Transmission.class, transmission_id),
                        Hibernate.instOf().getInstanceById(BodyType.class, body_type_id),
                        Hibernate.instOf().getInstanceById(EngineType.class, engine_type_id),
                        mileage,
                        Hibernate.instOf().getInstanceById(WheelDriveType.class, wheelDriveType_id))
                );
        for (String catId : category_ids) {
            item.addCategory(
                    Hibernate.instOf().getInstanceById(
                            Category.class, Long.parseLong(catId)
                    )
            );
        }
        ArrayList<Image> list = (ArrayList<Image>) req.getSession().getAttribute("imagesAdd");
        if (list.size() != 0) {
            for (Image image : list) {
                item.addImage(image);
            }
        }
        Hibernate.instOf().persistModel(item);
        req.getSession().setAttribute("imagesAdd" , new ArrayList<Image>());
        LOG.info(MARKER,"Server store to db item: {}", item);
        resp.sendRedirect(req.getContextPath() + "/main.do");
    }
}