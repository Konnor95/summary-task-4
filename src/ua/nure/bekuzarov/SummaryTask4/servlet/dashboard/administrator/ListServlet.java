package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.administrator;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing a list of administrators.
 */
@WebServlet(Actions.Dashboard.Administrators.LIST)
public class ListServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getResult(req);
        req.setAttribute("administrators", getUserService().getByRole(UserRole.ADMIN));
        forward(Pages.Dashboard.Administrators.LIST, req, resp);
    }

}
