package ua.nure.bekuzarov.SummaryTask4.servlet.main;

import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.servlet.BaseServlet;
import ua.nure.bekuzarov.SummaryTask4.util.ProcessResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Base servlet for all servlets of main module.
 */
public abstract class MainServlet extends BaseServlet {

    @Override
    protected String getUserAttributeName() {
        return Attributes.CURRENT_USER;
    }

    @Override
    protected void forward(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getCurrentUser(req) != null) {
            Map<Integer, Integer> books = getOrderedBooks(req);
            req.setAttribute("orderedBooks", books);
            req.setAttribute("orderedBooksCount", count(books));
        }
        super.forward(page, req, resp);
    }

    protected void getResult(HttpServletRequest req) {
        HttpSession session = req.getSession();
        ProcessResult result = (ProcessResult) session.getAttribute(Attributes.PROCESS_RESULT_MAIN);
        req.setAttribute(Attributes.PROCESS_RESULT, result);
        session.removeAttribute(Attributes.PROCESS_RESULT_MAIN);
    }

    protected void setResult(HttpServletRequest req, boolean succeeded, String message) {
        HttpSession session = req.getSession();
        ProcessResult result = new ProcessResult();
        result.setSucceeded(succeeded);
        result.setMessage(getTranslator().translate(message, getLocale(req)));
        session.setAttribute(Attributes.PROCESS_RESULT_MAIN, result);
    }

    @SuppressWarnings("unchecked")
    protected Map<Integer, Integer> getOrderedBooks(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Map<Integer, Integer> orderedBooks = (Map<Integer, Integer>) session.getAttribute(Attributes.ORDERED_BOOKS);
        return orderedBooks == null ? new HashMap<Integer, Integer>() : orderedBooks;
    }

    @SuppressWarnings("unchecked")
    protected Map<Integer, Integer> updateOrderList(HttpServletRequest req, int bookId, int quantity) {
        HttpSession session = req.getSession();
        Map<Integer, Integer> orderedBooks = (Map<Integer, Integer>) session.getAttribute(Attributes.ORDERED_BOOKS);

        if (orderedBooks == null) {
            orderedBooks = new HashMap<>();
        }
        if (quantity <= 0) {
            orderedBooks.remove(bookId);
        } else {
            orderedBooks.put(bookId, quantity);
        }
        session.setAttribute(Attributes.ORDERED_BOOKS, orderedBooks);
        return orderedBooks;
    }

    protected void unsetOrderList(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute(Attributes.ORDERED_BOOKS);
    }

    private int count(Map<Integer, Integer> books) {
        int sum = 0;
        for (Integer value : books.values()) {
            sum += value;
        }
        return sum;
    }

}
