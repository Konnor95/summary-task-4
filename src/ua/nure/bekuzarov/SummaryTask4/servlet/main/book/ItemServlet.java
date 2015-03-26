package ua.nure.bekuzarov.SummaryTask4.servlet.main.book;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.servlet.main.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing a book.
 */
@WebServlet(Actions.Main.Books.ITEM)
public class ItemServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBook(req, resp);
        if (book == null) {
            return;
        }
        req.setAttribute(Attributes.BOOK, book);
        forward(Pages.Main.Books.ITEM, req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBook(req, resp);
        if (book == null) {
            return;
        }
        Integer quantity = getIntParam(req, "quantity");
        if (quantity == null) {
            redirectBack(req, resp);
            return;
        }
        updateOrderList(req, book.getId(), quantity);
        redirectToAction(Actions.Main.Books.ITEM + "?id=" + book.getId(), req, resp);

    }

    private Book getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectToAction(Actions.Main.Books.LIST, req, resp);
    }

}
