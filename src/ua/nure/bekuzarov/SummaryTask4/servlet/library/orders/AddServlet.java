package ua.nure.bekuzarov.SummaryTask4.servlet.library.orders;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Order;
import ua.nure.bekuzarov.SummaryTask4.entity.OrderType;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryServlet;
import ua.nure.bekuzarov.SummaryTask4.validator.OrderValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Checks out book to the reading room(s).
 */
@WebServlet(Actions.Library.ADD_ORDER)
public class AddServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Pages.Library.ADD_ORDER, req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = getOrder(req, resp);
        if (order == null) {
            return;
        }
        getOrderService().add(order);
        setResult(req, true, "result.bookCheckedOut");
        redirectToAction(Actions.Library.ReadingRooms.LIST, req, resp);
    }


    private Order getOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookId = getIntParam(req, "bookId");
        Integer subscriptionId = getIntParam(req, "subscriptionId");
        Validator validator = new OrderValidator(bookId, subscriptionId, getTranslator(), getLocale(req));
        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, bookId, subscriptionId);
            return null;
        }
        Order order = new Order();
        order.setBookId(bookId);
        order.setSubscriptionId(subscriptionId);
        order.setType(OrderType.READING_ROOM);
        order.setDueDate(new Date(new java.util.Date().getTime()));
        if (!getBookService().exists(bookId)) {
            validator.put("bookId", "validate.bookNotFound");
            forwardBack(req, resp, validator, bookId, subscriptionId);
            return null;
        }
        if (!getUserService().readerExists(subscriptionId)) {
            validator.put("subscriptionId", "validate.userNotFound");
            forwardBack(req, resp, validator, bookId, subscriptionId);
            return null;
        }
        return order;
    }

    private void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, Integer bookId, Integer subscriptionId)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute("bookId", bookId);
        req.setAttribute("subscriptionId", subscriptionId);
        forward(Pages.Library.ADD_ORDER, req, resp);
    }

}
