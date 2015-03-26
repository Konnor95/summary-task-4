package ua.nure.bekuzarov.SummaryTask4.servlet.main.cart;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.servlet.main.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for updating cart.
 */
@WebServlet(Actions.Main.Cart.UPDATE_ITEM)
public class CartUpdateServlet extends MainServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, "id");
        Integer quantity = getIntParam(req, "quantity");
        if (id != null && quantity != null) {
            updateOrderList(req, id, quantity);
        }
    }

}
