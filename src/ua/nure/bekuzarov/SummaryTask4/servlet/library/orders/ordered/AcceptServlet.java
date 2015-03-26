package ua.nure.bekuzarov.SummaryTask4.servlet.library.orders.ordered;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Order;
import ua.nure.bekuzarov.SummaryTask4.entity.OrderType;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryServlet;
import ua.nure.bekuzarov.SummaryTask4.util.Settings;
import ua.nure.bekuzarov.SummaryTask4.validator.OrderValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

/**
 * Responsible for accepting orders (checks books out).
 */
@WebServlet(Actions.Library.Ordered.ACCEPT)
public class AcceptServlet extends LibraryServlet {

    private static final String DAYS = "days";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = getOrder(req, resp);
        if (order == null) {
            return;
        }
        req.setAttribute("order", order);
        req.setAttribute("max", Settings.getMaxCheckingOut());
        forward(Pages.Library.Ordered.ACCEPT, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = getOrder(req, resp);
        if (order == null) {
            return;
        }
        Integer days = getIntParam(req, DAYS);
        Validator validator = new OrderValidator(days, getTranslator(), getLocale(req));
        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, days);
            return;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        c.add(Calendar.DATE, days);
        Date dueDate = new Date(c.getTime().getTime());
        order.setDueDate(dueDate);
        order.setType(OrderType.CHECKED_OUT);
        getOrderService().update(order);
        setResult(req, true, "result.orderAccepted");
        redirectToAction(Actions.Library.Ordered.LIST, req, resp);
    }

    private Order getOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter(Attributes.ID);
        if (param == null) {
            redirectBack(req, resp);
            return null;
        }
        int id;
        try {
            id = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            redirectBack(req, resp);
            return null;
        }
        Order order = getOrderService().getById(id);
        if (order == null || order.getType() != OrderType.ORDERED) {
            redirectBack(req, resp);
            return null;
        }
        return order;
    }

    private void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.orderNotFound");
        redirectToAction(Actions.Library.Ordered.LIST, req, resp);
    }

    private void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, Integer days)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute(DAYS, days);
        forward(Pages.Library.Ordered.ACCEPT, req, resp);
    }

}
