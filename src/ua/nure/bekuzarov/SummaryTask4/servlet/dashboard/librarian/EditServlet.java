package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.librarian;

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
 * Responsible for librarian editing.
 */
@WebServlet(Actions.Dashboard.Librarians.EDIT)
public class EditServlet extends LibrarianServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getLibrarian(req, resp);
        if (user == null) {
            return;
        }
        req.setAttribute(Attributes.LIBRARIAN, user);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User existingUser = getLibrarian(req, resp);
        if (existingUser == null) {
            return;
        }
        User user = new User();
        user.setId(existingUser.getId());
        user.setFirstName(getStringParam(req, "firstName"));
        user.setLastName(getStringParam(req, "lastName"));
        user.setEmail(getStringParam(req, "email"));
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
        setResult(req, true, "result.librarianEdited");
        redirectToAction(Actions.Dashboard.Librarians.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Librarians.EDIT;
    }

}
