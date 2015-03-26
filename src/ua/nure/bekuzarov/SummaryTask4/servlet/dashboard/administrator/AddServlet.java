package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.administrator;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.validator.UserValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for adding a new librarian.
 */
@WebServlet(Actions.Dashboard.Administrators.ADD)
public class AddServlet extends AdministratorServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        String locale = getLocale(req);
        Validator validator = new UserValidator(user, getTranslator(), locale);
        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, user);
            return;
        }
        checkUniqueness(user, validator);
        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, user);
            return;
        }
        user.setRole(UserRole.ADMIN);
        user.setPassword(hash(user.getPassword()));
        getUserService().add(user);
        setResult(req, true, "result.administratorAdded");
        redirectToAction(Actions.Dashboard.Administrators.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Administrators.ADD;
    }
}
