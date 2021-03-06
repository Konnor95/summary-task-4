package ua.nure.bekuzarov.SummaryTask4.servlet.library.orders.checkedOut;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Order;
import ua.nure.bekuzarov.SummaryTask4.entity.OrderType;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Responsible for viewing a list of checked out books.
 */
@WebServlet(Actions.Library.CheckedOut.LIST)
public class ListServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = getOrderService().getAll(OrderType.CHECKED_OUT);
        req.setAttribute("orders", orders);
        getResult(req);
        forward(Pages.Library.CheckedOut.LIST, req, resp);
    }

}
