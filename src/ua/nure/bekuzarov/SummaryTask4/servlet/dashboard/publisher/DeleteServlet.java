package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.publisher;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getUploadBooksDirectory;

/**
 * Responsible for publisher deleting.
 */
@WebServlet(Actions.Dashboard.Publishers.DELETE)
public class DeleteServlet extends PublisherServlet {
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
        Publisher publisher = getPublisher(req, resp);
        if (publisher == null) {
            return;
        }
        int count = getOrderService().countByPublisher(publisher);
        if (count != 0) {
            setResult(req, false, "result.cannotDeletePublisher");
            redirectToAction(getForwardPage(), req, resp);
            return;
        }
        List<String> booksPhotos = getBookService().getImagesByPublisher(publisher);
        getFileService().removeFiles(booksPhotos, getUploadBooksDirectory());
        getPublisherService().delete(publisher);
        setResult(req, true, "result.publisherDeleted");
        redirectToAction(Actions.Dashboard.Publishers.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Publishers.DELETE;
    }
}
