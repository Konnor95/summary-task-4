package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.user;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Reader;
import ua.nure.bekuzarov.SummaryTask4.entity.Subscription;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Responsible for extending overdue subscriptions of readers .
 */
@WebServlet(Actions.Dashboard.Users.EXTEND_OVERDUE)
public class ExtendServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getReader(req, resp);
        if (user == null) {
            return;
        }
        req.setAttribute(Attributes.USER, user);
        req.setAttribute("submitUrl", getUrl());
        req.setAttribute("backUrl", getRedirectAction());
        forward(Pages.Dashboard.Users.EXTEND, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader user = getReader(req, resp);
        if (user == null) {
            return;
        }
        setExpirationDate(user, 12);
        getUserService().updateSubscription(user);
        setResult(req, true, "result.subscription.extended");
        redirectToAction(getRedirectAction(), req, resp);
    }

    protected String getUrl() {
        return Actions.Dashboard.Users.EXTEND_OVERDUE;
    }

    private Reader getReader(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Reader user = getUserService().getReaderById(id);
        if (user == null || (user.getRole() != UserRole.OVERDUE && user.getRole() != UserRole.USER)) {
            redirectBack(req, resp);
            return null;
        }
        return user;
    }

    protected String getRedirectAction() {
        return Actions.Dashboard.Users.OVERDUE;
    }

    private void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.userNotFound");
        redirectToAction(getRedirectAction(), req, resp);
    }

    private void setExpirationDate(Reader reader, int months) {
        Subscription subscription = reader.getSubscription();
        Calendar cal = Calendar.getInstance();
        if (reader.getRole() == UserRole.USER) {
            cal.setTime(subscription.getExpirationDate());
        } else {
            cal.setTime(new Date());
        }
        cal.add(Calendar.MONTH, months);
        subscription.setExpirationDate(new java.sql.Date(cal.getTime().getTime()));
        reader.setSubscription(subscription);
    }

}
