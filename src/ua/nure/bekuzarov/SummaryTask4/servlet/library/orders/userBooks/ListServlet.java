package ua.nure.bekuzarov.SummaryTask4.servlet.library.orders.userBooks;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.UserBook;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(Actions.Library.USER_BOOKS)
public class ListServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserBook> userBooks = getUserService().getBooks();
        req.setAttribute("userBooks", userBooks);
        forward(Pages.Library.USER_BOOKS, req, resp);
    }
}
