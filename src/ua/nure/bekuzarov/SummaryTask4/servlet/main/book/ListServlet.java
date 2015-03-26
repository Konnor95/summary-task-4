package ua.nure.bekuzarov.SummaryTask4.servlet.main.book;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.servlet.main.MainServlet;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getBooksPerPage;

/**
 * Responsible for search books.
 */
@WebServlet(Actions.Main.Books.LIST)
public class ListServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderBy = getStringParam(req, "orderBy", "default");
        String search = getStringParam(req, "search");
        int page = getIntParam(req, "page", 1);
        Tuple<List<Book>, Integer> tuple = getBookService().getAll(
                page, getBooksPerPage(), search, orderBy, getLocales().keySet(), getLocale(req)
        );
        req.setAttribute(Attributes.BOOKS, tuple.getX());
        req.setAttribute("max", tuple.getY());
        req.setAttribute("current", page);
        req.setAttribute("sortOrders", getBookService().getSortOrders().keySet());
        req.setAttribute("orderByList", Arrays.asList("id", "title", "pTitle"));
        forward(Pages.Main.Books.LIST, req, resp);
    }

}
