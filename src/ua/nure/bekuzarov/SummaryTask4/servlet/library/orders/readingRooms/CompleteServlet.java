package ua.nure.bekuzarov.SummaryTask4.servlet.library.orders.readingRooms;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Order;
import ua.nure.bekuzarov.SummaryTask4.entity.OrderType;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for completing orders that were made in reading room(s)
 * (returns book back to library).
 */
@WebServlet(Actions.Library.ReadingRooms.COMPLETE)
public class CompleteServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = getOrder(req, resp);
        if (order == null) {
            return;
        }
        req.setAttribute("order", order);
        forward(Pages.Library.ReadingRooms.COMPLETE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = getOrder(req, resp);
        if (order == null) {
            return;
        }
        order.setType(OrderType.COMPLETED);
        getOrderService().update(order);
        setResult(req, true, "result.orderCompleted");
        redirectToAction(Actions.Library.ReadingRooms.LIST, req, resp);
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
        if (order == null || order.getType() != OrderType.READING_ROOM) {
            redirectBack(req, resp);
            return null;
        }
        return order;
    }

    private void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.orderNotFound");
        redirectToAction(Actions.Library.ReadingRooms.LIST, req, resp);
    }
}
