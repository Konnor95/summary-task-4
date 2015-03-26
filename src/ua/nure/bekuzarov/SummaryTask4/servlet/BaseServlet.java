package ua.nure.bekuzarov.SummaryTask4.servlet;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.util.LocaleUtil;
import ua.nure.bekuzarov.SummaryTask4.util.file.FileService;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Base servlet for module servlets.
 */
public abstract class BaseServlet extends AbstractServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServlet.class);

    private FileService fileService;

    @Override
    public void init() throws ServletException {
        super.init();
        fileService = (FileService) getServletContext().getAttribute(Attributes.FILE_SERVICE);
    }

    protected FileService getFileService() {
        return fileService;
    }

    protected User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(getUserAttributeName());
        return obj == null ? null : (User) obj;
    }

    protected void setCurrentUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(getUserAttributeName(), user);

    }

    protected void unsetCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(getUserAttributeName());
    }

    protected void forward(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(page).forward(req, resp);
    }

    protected void redirectToAction(String uri, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(renderPath(uri, req));
    }

    protected Translation getTranslation(HttpServletRequest req, String parameterName) {
        Translation translation = new Translation();
        for (String localeKey : getLocales().keySet()) {
            String value = getStringParam(req, parameterName + "_" + localeKey);
            setTranslationValue(translation, localeKey, value);
        }
        return translation;
    }

    protected void setTranslationValue(Translation translation, String localeKey, String value) {
        try {
            Field field = Translation.class.getDeclaredField(localeKey);
            field.setAccessible(true);
            field.set(translation, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.warn("Cannot set translation value.", e);
        }
    }

    protected void checkUniqueness(User user, Validator validator) {
        User existingUser = getUserService().getByLogin(user.getLogin(), user.getId());
        if (existingUser != null) {
            validator.put("login", "validate.loginTaken");
        }
        existingUser = getUserService().getByEmail(user.getEmail(), user.getId());
        if (existingUser != null) {
            validator.put("email", "validate.emailTaken");
        }
    }

    protected String hash(String sourceString) {
        return DigestUtils.sha256Hex(sourceString + "salt");
    }

    protected abstract String getUserAttributeName();

    private String renderPath(String uri, HttpServletRequest req) {
        return LocaleUtil.renderPath(uri, req, getDefaultLocale());
    }
}
