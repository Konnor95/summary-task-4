package ua.nure.bekuzarov.SummaryTask4.servlet.main.cart;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.servlet.main.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Responsible for deleting book from cart.
 */
@WebServlet(Actions.Main.Cart.DELETE_ITEM)
public class CartDeleteServlet extends MainServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, "id");

        if (id != null) {
            int left = updateOrderList(req, id, 0).size();
            if (left == 0) {
                PrintWriter writer = resp.getWriter();
                writer.write("true");
            }

        }
    }
}
