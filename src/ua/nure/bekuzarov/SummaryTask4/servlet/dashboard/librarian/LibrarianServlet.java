package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.librarian;

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
 * Base class for servlets that manage librarians.
 */
public abstract class LibrarianServlet extends DashboardServlet {

    protected User getLibrarian(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, Attributes.ID);
        if (id == null) {
            redirectBack(req, resp);
            return null;
        }
        User user = getUserService().getById(id);
        if (user == null || user.getRole() != UserRole.LIBRARIAN) {
            redirectBack(req, resp);
            return null;
        }
        return user;
    }

    protected void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.librarianNotFound");
        redirectToAction(Actions.Dashboard.Librarians.LIST, req, resp);
    }

    protected void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, User librarian)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute(Attributes.LIBRARIAN, librarian);
        forward(getForwardPage(), req, resp);
    }

    protected abstract String getForwardPage();

}
