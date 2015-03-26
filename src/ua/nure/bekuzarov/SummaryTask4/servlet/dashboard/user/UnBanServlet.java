package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.user;


import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for unbanning users.
 */
@WebServlet(Actions.Dashboard.Users.UNBAN)
public class UnBanServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req, resp);
        if (user == null) {
            return;
        }
        req.setAttribute(Attributes.USER, user);
        forward(Pages.Dashboard.Users.UNBAN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req, resp);
        if (user == null) {
            return;
        }
        user.setRole(UserRole.USER);
        getUserService().update(user);
        setResult(req, true, "result.userUnbanned");
        redirectToAction(Actions.Dashboard.Users.BLACK_LIST, req, resp);
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
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
        User user = getUserService().getById(id);
        if (user == null || user.getRole() != UserRole.BANNED) {
            redirectBack(req, resp);
            return null;
        }
        return user;
    }

    private void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.userNotFound");
        redirectToAction(Actions.Dashboard.Users.BLACK_LIST, req, resp);
    }

}
