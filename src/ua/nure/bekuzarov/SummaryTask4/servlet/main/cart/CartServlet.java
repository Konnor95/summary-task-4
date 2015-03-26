package ua.nure.bekuzarov.SummaryTask4.servlet.main.cart;


import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Order;
import ua.nure.bekuzarov.SummaryTask4.entity.Reader;
import ua.nure.bekuzarov.SummaryTask4.servlet.main.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for viewing cart and creating orders.
 */
@WebServlet(Actions.Main.Cart.CART)
public class CartServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getResult(req);
        List<Integer> ids = new ArrayList<>();
        ids.addAll(getOrderedBooks(req).keySet());
        if (ids.size() > 0) {
            List<Book> books = getBookService().getAll(ids);
            req.setAttribute(Attributes.BOOKS, books);
        }
        forward(Pages.Main.Books.CART, req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = getUserService().getReaderById(getCurrentUser(req));
        List<Order> orders = getOrderService().add(reader, getOrderedBooks(req));
        if (orders == null) {
            setResult(req, false, "result.notEnoughBooksInStock");
            redirectToAction(Actions.Main.Cart.CART, req, resp);
            return;
        }
        unsetOrderList(req);
        redirectToAction(Actions.Main.PROFILE, req, resp);
    }

}
