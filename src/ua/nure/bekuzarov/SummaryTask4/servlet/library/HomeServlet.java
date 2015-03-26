package ua.nure.bekuzarov.SummaryTask4.servlet.library;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.OrderType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing home page of library module.
 */
@WebServlet(Actions.Library.INDEX)
public class HomeServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orderedCount", getOrderService().countByType(OrderType.ORDERED));
        req.setAttribute("checkedOutCount", getOrderService().countByType(OrderType.CHECKED_OUT));
        req.setAttribute("completedCount", getOrderService().countByType(OrderType.COMPLETED));
        req.setAttribute("readingRoomsCount", getOrderService().countByType(OrderType.READING_ROOM));
        forward(Pages.Library.INDEX, req, resp);
    }

}
