package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.publisher;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;
import ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.DashboardServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base class for servlets that manage publishers.
 */
public abstract class PublisherServlet extends DashboardServlet {

    protected Publisher getPublisher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = getIntParam(req, Attributes.ID);
        if (id == null) {
            redirectBack(req, resp);
            return null;
        }
        Publisher publisher = getPublisherService().getById(id);
        if (publisher == null) {
            redirectBack(req, resp);
            return null;
        }
        return publisher;
    }

    protected Publisher getPublisherFromRequest(HttpServletRequest req, HttpServletResponse resp, boolean isNew) throws ServletException, IOException {
        Publisher publisher = new Publisher();
        if (!isNew) {
            Integer id = getIntParam(req, Attributes.ID);
            if (id == null) {
                redirectBack(req, resp);
                return null;
            }
            publisher.setId(id);
        }
        Translation title = getTranslation(req, Attributes.TITLE);
        publisher.setTitle(title);
        return publisher;
    }

    protected void redirectBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResult(req, false, "validate.publisherNotFound");
        redirectToAction(Actions.Dashboard.Publishers.LIST, req, resp);
    }

    protected void forwardBack(HttpServletRequest req, HttpServletResponse resp, Validator validator, Publisher publisher)
            throws ServletException, IOException {
        req.setAttribute("messages", validator.getMessages());
        req.setAttribute("publisher", publisher);
        forward(getForwardPage(), req, resp);
    }

    protected abstract String getForwardPage();

}
