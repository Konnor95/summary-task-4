package ua.nure.bekuzarov.SummaryTask4.api.servlet.book;

import ua.nure.bekuzarov.SummaryTask4.api.servlet.ApiServlet;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getBooksPerPage;

/**
 * Returns a list of books.
 */
@WebServlet("/api/books")
public class ListServlet extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = getIntParam(req, "page", 1);
        int count = getIntParam(req, "count", getBooksPerPage());
        String locale = getStringParam(req, "lang", getDefaultLocale());
        String orderBy = getStringParam(req, "orderBy", "default");
        String search = getStringParam(req, "search");
        List<Book> books = getBookService().getAll(page, count, search, orderBy, getLocales().keySet(), locale).getX();
        print(req, resp, books, Book.class);
    }

}
