package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.librarian;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for librarian deleting.
 */
@WebServlet(Actions.Dashboard.Librarians.DELETE)
public class DeleteServlet extends LibrarianServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getLibrarian(req, resp);
        if (user == null) {
            return;
        }
        req.setAttribute(Attributes.LIBRARIAN, user);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getLibrarian(req, resp);
        if (user == null) {
            return;
        }
        getUserService().delete(user);
        setResult(req, true, "result.librarianDeleted");
        redirectToAction(Actions.Dashboard.Librarians.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Librarians.DELETE;
    }

}
