package ua.nure.bekuzarov.SummaryTask4.api.servlet.book;

import ua.nure.bekuzarov.SummaryTask4.api.servlet.ApiServlet;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a book with the specified id.
 */
@WebServlet("/api/book/*")
public class ItemServlet extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getId(req, resp);
        if (id == null) {
            return;
        }
        Book book = getBookService().getById(id);
        if (book == null) {
            resp.sendError(404);
            return;
        }
        print(req, resp, book);
    }

}
