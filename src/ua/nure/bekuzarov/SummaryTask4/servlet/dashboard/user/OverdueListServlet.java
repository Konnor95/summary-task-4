package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.user;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing a list of reader with overdue subscriptions.
 */
@WebServlet(Actions.Dashboard.Users.OVERDUE)
public class OverdueListServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getResult(req);
        req.setAttribute(Attributes.USERS, getUserService().getByRole(UserRole.OVERDUE));
        forward(Pages.Dashboard.Users.OVERDUE_LIST, req, resp);
    }
}
