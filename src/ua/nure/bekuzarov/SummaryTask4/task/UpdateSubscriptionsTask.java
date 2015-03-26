package ua.nure.bekuzarov.SummaryTask4.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.service.user.UserService;
import ua.nure.bekuzarov.SummaryTask4.service.user.UserServiceImpl;

import javax.servlet.ServletContext;

/**
 * Updates subscriptions.
 */
public final class UpdateSubscriptionsTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateSubscriptionsTask.class);
    private UserService userService;

    /**
     * Creates a new {@code UpdateSubscriptionsTask}.
     *
     * @param context servlet context
     */
    public UpdateSubscriptionsTask(ServletContext context) {
        userService = (UserService) context.getAttribute(UserServiceImpl.class.getName());
    }

    @Override
    public void run() {
        LOGGER.info("Updating subscriptions...");
        userService.updateSubscriptions();
        LOGGER.info("Subscriptions updated.");
    }

}
