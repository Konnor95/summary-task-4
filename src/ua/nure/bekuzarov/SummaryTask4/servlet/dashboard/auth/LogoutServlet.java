package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.auth;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for logging administrators out.
 */
@WebServlet(Actions.Dashboard.LOGOUT)
public class LogoutServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        unsetCurrentUser(req);
        redirectToAction(Actions.Dashboard.LOGIN, req, resp);
    }

}
