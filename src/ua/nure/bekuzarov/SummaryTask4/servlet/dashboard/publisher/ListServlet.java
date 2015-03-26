package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.publisher;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing a list of publishers.
 */
@WebServlet(Actions.Dashboard.Publishers.LIST)
public class ListServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getResult(req);
        req.setAttribute(Attributes.PUBLISHERS, getPublisherService().getAll());
        forward(Pages.Dashboard.Publishers.LIST, req, resp);
    }

}
