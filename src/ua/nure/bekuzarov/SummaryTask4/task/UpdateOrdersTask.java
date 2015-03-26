package ua.nure.bekuzarov.SummaryTask4.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.service.order.OrderService;
import ua.nure.bekuzarov.SummaryTask4.service.order.OrderServiceImpl;

import javax.servlet.ServletContext;

/**
 * Updates orders.
 */
public final class UpdateOrdersTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateOrdersTask.class);
    private OrderService orderService;

    /**
     * Creates a new {@code UpdateOrdersTask}.
     *
     * @param context servlet context
     */
    public UpdateOrdersTask(ServletContext context) {
        orderService = (OrderService) context.getAttribute(OrderServiceImpl.class.getName());
    }

    @Override
    public void run() {
        LOGGER.info("Updating orders...");
        orderService.updateOrders();
        LOGGER.info("Orders updated.");
    }

}
