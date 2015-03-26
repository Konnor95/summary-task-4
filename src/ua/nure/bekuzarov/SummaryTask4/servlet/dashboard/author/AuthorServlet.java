package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.author;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base class for servlets that manage authors.
 */
public abstract class AuthorServlet extends DashboardServlet {

    protected Author getAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, Attributes.ID);
        if (id == null) {
            redirectBack(req, resp);
            return null;
        }
        Author author = getAuthorService().getById(id);
        if (author == null) {
            redirectBack(req, resp);
            return null;
        }
        return author;
    }

    protected void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.authorNotFound");
        redirectToAction(Actions.Dashboard.Authors.LIST, req, resp);
    }

    protected void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, Author author)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute("author", author);
        forward(getForwardPage(), req, resp);
    }

    protected Author getAuthorFromRequest(HttpServletRequest req, HttpServletResponse resp, boolean isNew) throws ServletException, IOException {
        Author author = new Author();
        if (!isNew) {
            Integer id = getIntParam(req, Attributes.ID);
            if (id == null) {
                redirectBack(req, resp);
                return null;
            }
            author.setId(id);
        }
        Translation name = getTranslation(req, Attributes.NAME);
        Translation description = getTranslation(req, Attributes.DESCRIPTION);
        author.setName(name);
        author.setDescription(description);
        return author;
    }

    protected abstract String getForwardPage();
}
