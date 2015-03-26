package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.administrator;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for administrator deleting.
 */
@WebServlet(Actions.Dashboard.Administrators.DELETE)
public class DeleteServlet extends AdministratorServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAdmin(req, resp);
        if (user == null) {
            return;
        }
        if (user.getId() == getCurrentUser(req).getId()) {
            setResult(req, false, "validate.cannotDeleteYourself");
            redirectToAction(Actions.Dashboard.Administrators.LIST, req, resp);
            return;
        }
        req.setAttribute(Attributes.ADMINISTRATOR, user);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAdmin(req, resp);
        if (user == null) {
            return;
        }
        if (user.getId() == getCurrentUser(req).getId()) {
            setResult(req, false, "validate.cannotDeleteYourself");
            redirectToAction(Actions.Dashboard.Administrators.LIST, req, resp);
            return;
        }
        getUserService().delete(user);
        setResult(req, true, "result.administratorDeleted");
        redirectToAction(Actions.Dashboard.Administrators.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Administrators.DELETE;
    }

}
