package ua.nure.bekuzarov.SummaryTask4.servlet.main;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing a home page.
 */
@WebServlet(Actions.Main.INDEX)
public class HomeServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = (String) req.getAttribute("url");
        if (url == null || url.isEmpty()) {
            resp.sendError(404);
            return;
        }
        url = url.replace(req.getContextPath(), "");
        if (!url.equals("/")) {
            resp.sendError(404);
            return;
        }
        forward(Pages.Main.INDEX, req, resp);
    }

}
