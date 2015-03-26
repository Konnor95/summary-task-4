package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.publisher;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
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
 * Responsible for publisher editing.
 */
@WebServlet(Actions.Dashboard.Publishers.EDIT)
public class EditServlet extends PublisherServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Publisher publisher = getPublisher(req, resp);
        if (publisher == null) {
            return;
        }
        req.setAttribute(Attributes.PUBLISHER, publisher);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Publisher publisher = getPublisherFromRequest(req, resp, false);
        if (publisher == null) {
            return;
        }

        Publisher existingPublisher = getPublisherService().getById(publisher.getId());
        if (existingPublisher == null) {
            redirectBack(req, resp);
            return;
        }

        Validator validator = new PublisherValidator(publisher, getTranslator(), getLocale(req), getLocales());

        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, publisher);
            return;
        }

        updatePublisher(existingPublisher, publisher);
        setResult(req, true, "result.publisherEdited");
        redirectToAction(Actions.Dashboard.Publishers.LIST, req, resp);

    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Publishers.EDIT;
    }


    private void updatePublisher(Publisher existingPublisher, Publisher publisher) {
        Translation title = existingPublisher.getTitle();
        title.clone(publisher.getTitle());
        getTranslationService().update(title);
    }

}
