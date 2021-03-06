package ua.nure.bekuzarov.SummaryTask4.servlet.library.auth;


import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryServlet;
import ua.nure.bekuzarov.SummaryTask4.validator.UserValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for librarians readers in.
 */
@WebServlet(Actions.Library.LOGIN)
public class LoginServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        if (user != null) {
            redirectToAction(Actions.Library.INDEX, req, resp);
            return;
        }
        forward(Pages.Library.LOGIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String locale = getLocale(req);
        Validator validator = new UserValidator(login, password, getTranslator(), locale);
        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, login, password);
            return;
        }
        User user = getUserService().getByLogin(login);
        if (user == null) {
            validator.put("login", "validate.invalidLogin");
            forwardBack(req, resp, validator, login, password);
            return;
        }
        if (!user.getPassword().equals(hash(password))) {
            validator.put("password", "validate.invalidPassword");
            forwardBack(req, resp, validator, login, password);
            return;
        }
        if (user.getRole() != UserRole.LIBRARIAN) {
            validator.put("message", "validate.noRights");
            forwardBack(req, resp, validator, login, password);
            return;
        }
        setCurrentUser(req, user);
        redirectToAction(Actions.Library.INDEX, req, resp);
    }

    private void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, String login, String password)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute("login", login);
        req.setAttribute("password", password);
        forward(Pages.Library.LOGIN, req, resp);
    }
}
