package ua.nure.bekuzarov.SummaryTask4.servlet.library.auth;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for logging librarians out.
 */
@WebServlet(Actions.Library.LOGOUT)
public class LogoutServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        unsetCurrentUser(req);
        redirectToAction(Actions.Library.LOGIN, req, resp);
    }

}
