package ua.nure.bekuzarov.SummaryTask4.servlet.main.author;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.servlet.main.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing an author.
 */
@WebServlet(Actions.Main.Authors.ITEM)
public class ItemServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = getAuthor(req, resp);
        if (author == null) {
            return;
        }
        req.setAttribute(Attributes.AUTHOR, author);
        req.setAttribute(Attributes.BOOKS, getBookService().getAll(author));
        forward(Pages.Main.Authors.ITEM, req, resp);
    }

    private Author getAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectToAction(Actions.Main.INDEX, req, resp);
    }

}
