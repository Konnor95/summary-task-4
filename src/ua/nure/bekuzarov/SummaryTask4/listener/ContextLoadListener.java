package ua.nure.bekuzarov.SummaryTask4.listener;

import ua.nure.bekuzarov.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.db.manager.HikariCPManager;
import ua.nure.bekuzarov.SummaryTask4.util.context.ContextLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ContextLoadListener class.
 *
 * @author Dmitry Bekuzarov
 */

public final class ContextLoadListener implements ServletContextListener {

    private ContextLoader loader;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loader = new ContextLoader(
                sce.getServletContext(),
                new ThreadLocalConnectionHolder(),
                new HikariCPManager()
        );
        loader.load(
                "ua.nure.bekuzarov.SummaryTask4.db",
                "ua.nure.bekuzarov.SummaryTask4.service",
                "ua.nure.bekuzarov.SummaryTask4.api.serialization"
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        loader.destroy(sce.getServletContext());
    }

}
