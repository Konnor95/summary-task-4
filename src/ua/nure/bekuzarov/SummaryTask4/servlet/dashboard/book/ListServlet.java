package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.book;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Responsible for viewing a list of books.
 */
@WebServlet(Actions.Dashboard.Books.LIST)
public class ListServlet extends DashboardServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = getBookService().getAll();
        req.setAttribute(Attributes.BOOKS, books);
        getResult(req);
        forward(Pages.Dashboard.Books.LIST, req, resp);
    }
}
