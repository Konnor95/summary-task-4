package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;
import ua.nure.bekuzarov.SummaryTask4.util.LocaleUtil;
import ua.nure.bekuzarov.SummaryTask4.util.mail.MailConfig;
import ua.nure.bekuzarov.SummaryTask4.util.mail.MailUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for confirmation of registered users.
 */
@WebServlet(Actions.Dashboard.Users.CONFIRM)
public class ConfirmServlet extends DashboardServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req, resp);
        if (user == null) {
            return;
        }
        req.setAttribute(Attributes.USER, user);
        forward(Pages.Dashboard.Users.CONFIRM, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req, resp);
        if (user == null) {
            return;
        }
        getUserService().confirm(user);
        try {
            sendEmail(req, user);
        } catch (MessagingException e) {
            LOGGER.warn("Message was not sent.", e);
            return;
        }
        setResult(req, true, "result.userConfirmed");
        redirectToAction(Actions.Dashboard.Users.NOT_CONFIRMED, req, resp);
    }

    private void sendEmail(HttpServletRequest req, User user) throws MessagingException {
        String locale = getLocale(req);
        String from = MailConfig.getEmail();
        String to = user.getEmail();
        String template = MailUtil.getTemplate("/templates/mail/userConfirmed.html");
        String title = getTranslator().translate("mail.userConfirmed.title", locale);
        template = template
                .replace("${lang}", locale)
                .replace("${title}", title)
                .replace("${body}", getTranslator().translate("mail.userConfirmed.body", locale))
                .replace("${link}", getLoginAbsolutePath(req))
                .replace("${linkText}", getTranslator().translate("text.log-in", locale).toLowerCase());
        MailUtil.send(from, to, title, template);
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
        if (user == null || user.getRole() != UserRole.NOT_CONFIRMED) {
            redirectBack(req, resp);
            return null;
        }
        return user;
    }

    private void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.userNotFound");
        redirectToAction(Actions.Dashboard.Users.NOT_CONFIRMED, req, resp);
    }

    private String getLoginAbsolutePath(HttpServletRequest req) {
        return LocaleUtil.renderAbsolutePath(Actions.Main.LOGIN, req, getDefaultLocale());
    }

}
