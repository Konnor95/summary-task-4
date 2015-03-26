package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.administrator;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.validator.UserValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for administrator editing.
 */
@WebServlet(Actions.Dashboard.Administrators.EDIT)
public class EditServlet extends AdministratorServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAdmin(req, resp);
        if (user == null) {
            return;
        }
        req.setAttribute(Attributes.ADMINISTRATOR, user);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, Attributes.ID);
        if (id == null) {
            redirectBack(req, resp);
            return;
        }
        User existingUser = getUserService().getById(id);
        if (existingUser == null) {
            redirectBack(req, resp);
            return;
        }
        User user = new User();
        user.setId(existingUser.getId());
        user.setFirstName(getStringParam(req, "firstName"));
        user.setLastName(getStringParam(req, "lastName"));
        user.setEmail(req.getParameter("email"));
        user.setLogin(getStringParam(req, "login"));
        boolean changePassword = Boolean.parseBoolean(req.getParameter("changePassword"));
        user.setPassword(changePassword ? getStringParam(req, "password") : existingUser.getPassword());
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
        user.setRole(existingUser.getRole());
        if (changePassword) {
            user.setPassword(hash(user.getPassword()));
        }
        getUserService().update(user);
        setResult(req, true, "result.administratorEdited");
        redirectToAction(Actions.Dashboard.Administrators.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Administrators.EDIT;
    }

}
