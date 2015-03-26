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
 * Responsible for viewing a list of all not confirmed users.
 */
@WebServlet(Actions.Dashboard.Users.NOT_CONFIRMED)
public class NotConfirmedListServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getResult(req);
        req.setAttribute(Attributes.USERS, getUserService().getByRole(UserRole.NOT_CONFIRMED));
        forward(Pages.Dashboard.Users.NOT_CONFIRMED, req, resp);
    }
}
