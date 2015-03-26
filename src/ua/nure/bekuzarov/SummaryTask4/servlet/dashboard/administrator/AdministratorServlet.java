package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.administrator;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base class for servlets that manage administrators.
 */
public abstract class AdministratorServlet extends DashboardServlet {

    protected User getAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, Attributes.ID);
        if (id == null) {
            redirectBack(req, resp);
            return null;
        }
        User user = getUserService().getById(id);
        if (user == null || user.getRole() != UserRole.ADMIN) {
            redirectBack(req, resp);
            return null;
        }
        return user;
    }

    protected void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.administratorNotFound");
        redirectToAction(Actions.Dashboard.Administrators.LIST, req, resp);
    }

    protected void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, User admin)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute(Attributes.ADMINISTRATOR, admin);
        forward(getForwardPage(), req, resp);
    }

    protected abstract String getForwardPage();

}
