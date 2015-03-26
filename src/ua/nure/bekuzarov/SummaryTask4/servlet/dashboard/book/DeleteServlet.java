package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.book;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getUploadBooksDirectory;

/**
 * Responsible for book deleting.
 */
@WebServlet(Actions.Dashboard.Books.DELETE)
public class DeleteServlet extends BookServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBook(req, resp);
        if (book == null) {
            return;
        }
        req.setAttribute(Attributes.BOOK, book);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBook(req, resp);
        if (book == null) {
            return;
        }
        int count = getOrderService().countByBook(book);
        if (count != 0) {
            setResult(req, false, "result.cannotDeleteBook");
            redirectToAction(Actions.Dashboard.Books.DELETE, req, resp);
            return;
        }

        getFileService().removeFile(book.getImage(), getUploadBooksDirectory());
        getBookService().delete(book);
        setResult(req, true, "result.bookDeleted");
        redirectToAction(Actions.Dashboard.Books.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Books.DELETE;
    }

}
