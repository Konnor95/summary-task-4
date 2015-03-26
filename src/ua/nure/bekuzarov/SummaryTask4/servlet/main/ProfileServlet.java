package ua.nure.bekuzarov.SummaryTask4.servlet.main;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Reader;
import ua.nure.bekuzarov.SummaryTask4.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for viewing profile of current user.
 */
@WebServlet(Actions.Main.PROFILE)
public class ProfileServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        if (user == null) {
            redirectToAction(Actions.Main.LOGIN, req, resp);
            return;
        }
        Reader reader = getUserService().getReaderById(user);
        req.setAttribute("reader", reader);
        req.setAttribute("ordered", getOrderService().getOrdered(reader));
        req.setAttribute("checkedOut", getOrderService().getCheckedOut(reader));
        forward(Pages.Main.PROFILE, req, resp);
    }

}
