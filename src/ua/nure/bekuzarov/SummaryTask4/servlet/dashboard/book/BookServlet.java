package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.book;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base class for servlets that manage books.
 */
public abstract class BookServlet extends DashboardServlet {

    protected Book getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, Attributes.ID);
        if (id == null) {
            redirectBack(req, resp);
            return null;
        }
        Book book = getBookService().getById(id);
        if (book == null) {
            redirectBack(req, resp);
            return null;
        }
        return book;
    }

    protected void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.bookNotFound");
        redirectToAction(Actions.Dashboard.Books.LIST, req, resp);
    }

    protected void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, Book book)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute(Attributes.BOOK, book);
        setSelect(req);
        forward(getForwardPage(), req, resp);
    }

    protected Book getBookFromRequest(HttpServletRequest req, HttpServletResponse resp, boolean isNew) throws ServletException, IOException {
        Book book = new Book();
        if (!isNew) {
            Integer id = getIntParam(req, Attributes.ID);
            if (id == null) {
                redirectBack(req, resp);
                return null;
            }
            book.setId(id);
        }
        Translation title = getTranslation(req, Attributes.TITLE);
        Translation description = getTranslation(req, Attributes.DESCRIPTION);
        book.setTitle(title);
        book.setDescription(description);
        book.setAmount(getIntParam(req, "amount"));
        book.setPages(getIntParam(req, "pages"));
        book.setYear(getIntParam(req, "year"));
        return book;
    }

    protected void setSelect(HttpServletRequest req) {
        req.setAttribute(Attributes.AUTHORS, getAuthorService().getAll());
        req.setAttribute(Attributes.PUBLISHERS, getPublisherService().getAll());
    }

    protected abstract String getForwardPage();


}
