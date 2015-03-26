package ua.nure.bekuzarov.SummaryTask4.servlet.main;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Responsible for registration of new readers.
 */
@WebServlet(Actions.Main.REGISTRATION)
public class RegistrationServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getCurrentUser(req) != null) {
            redirectToAction(Actions.Main.PROFILE, req, resp);
            return;
        }
        HttpSession session = req.getSession();
        Object message = session.getAttribute(Attributes.REGISTRATION_SUCCEED);
        if (message == null) {
            forward(Pages.Main.REGISTRATION, req, resp);
            return;
        }
        session.removeAttribute(Attributes.REGISTRATION_SUCCEED);
        forward(Pages.Main.REGISTRATION_SUCCEED, req, resp);
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
        user.setPassword(hash(user.getPassword()));
        User addedUser = getUserService().add(user);
        if (addedUser == null) {
            validator.put("error", "validate.error500");
            forwardBack(req, resp, validator, user);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute(Attributes.REGISTRATION_SUCCEED, "true");
        redirectToAction(Actions.Main.REGISTRATION, req, resp);
    }

    private void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, User user)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute(Attributes.USER, user);
        forward(Pages.Main.REGISTRATION, req, resp);
    }
}
