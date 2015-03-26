package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.author;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getUploadAuthorsDirectory;
import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getUploadBooksDirectory;

/**
 * Responsible for author deleting.
 */
@WebServlet(Actions.Dashboard.Authors.DELETE)
public class DeleteServlet extends AuthorServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = getAuthor(req, resp);
        if (author == null) {
            return;
        }
        req.setAttribute(Attributes.AUTHOR, author);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = getAuthor(req, resp);
        if (author == null) {
            return;
        }
        int count = getOrderService().countByAuthor(author);
        if (count != 0) {
            setResult(req, false, "result.cannotDeleteAuthor");
            redirectToAction(Actions.Dashboard.Authors.DELETE, req, resp);
            return;
        }

        List<String> booksPhotos = getBookService().getImagesByAuthor(author);
        getFileService().removeFiles(booksPhotos, getUploadBooksDirectory());
        getFileService().removeFile(author.getImage(), getUploadAuthorsDirectory());
        getAuthorService().delete(author);
        setResult(req, true, "result.authorDeleted");
        redirectToAction(Actions.Dashboard.Authors.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Authors.DELETE;
    }

}
