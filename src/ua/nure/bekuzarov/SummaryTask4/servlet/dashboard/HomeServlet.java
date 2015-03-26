package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing a admin module.
 */
@WebServlet(Actions.Dashboard.INDEX)
public class HomeServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("booksCount", getBookService().countAll());
        req.setAttribute("readersCount", getUserService().countByRole(UserRole.USER));
        req.setAttribute("publishersCount", getPublisherService().countAll());
        req.setAttribute("authorsCount", getAuthorService().countAll());
        forward(Pages.Dashboard.INDEX, req, resp);
    }

}
