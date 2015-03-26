package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.publisher;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.validator.PublisherValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsible for adding a new publisher.
 */
@WebServlet(Actions.Dashboard.Publishers.ADD)
public class AddServlet extends PublisherServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Publisher publisher = getPublisherFromRequest(req, resp, true);
        if (publisher == null) {
            return;
        }

        Validator validator = new PublisherValidator(publisher, getTranslator(), getLocale(req), getLocales());
        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, publisher);
            return;
        }

        savePublisher(publisher);
        setResult(req, true, "result.publisherAdded");
        redirectToAction(Actions.Dashboard.Publishers.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Publishers.ADD;
    }

    private Publisher savePublisher(Publisher publisher) {
        Translation title = getTranslationService().add(publisher.getTitle());
        publisher.setTitle(title);
        return getPublisherService().add(publisher);
    }

}
