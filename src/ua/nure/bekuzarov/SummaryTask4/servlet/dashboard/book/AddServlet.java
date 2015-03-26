package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.book;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.util.Settings;
import ua.nure.bekuzarov.SummaryTask4.validator.BookValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getUploadBooksDirectory;
import static ua.nure.bekuzarov.SummaryTask4.util.Settings.isImage;

/**
 * Responsible for adding a new book.
 */
@WebServlet(Actions.Dashboard.Books.ADD)
@MultipartConfig(
        location = Settings.Books.TEMP_DIRECTORY,
        fileSizeThreshold = Settings.Books.SIZE_THRESHOLD,
        maxFileSize = Settings.Books.MAX_SIZE
)
public class AddServlet extends BookServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setSelect(req);
        getResult(req);
        forward(getForwardPage(), req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBookFromRequest(req, resp, true);
        if (book == null) {
            return;
        }
        Integer publisherId = getIntParam(req, "publisherId");
        List<Integer> authorsIds = getIntParamValues(req, "authors");
        Validator validator = new BookValidator(book, publisherId, authorsIds, getTranslator(), getLocale(req), getLocales());
        Part imagePart = req.getPart(Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && !isImage(imagePart.getContentType())) {
            validator.put(Attributes.IMAGE, "validate.invalidFileFormat");
        }

        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, book);
            return;
        }
        book.setPublisher(getPublisherService().getById(publisherId));
        book.setAuthors(getAuthorService().getAll(authorsIds));
        saveBook(imagePart, book);
        setResult(req, true, "result.bookAdded");
        redirectToAction(Actions.Dashboard.Books.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Books.ADD;
    }

    private void saveBook(Part part, Book book) {
        Book savedBook = saveBook(book);
        if (part != null && part.getSize() != 0) {
            String image = getFileService().saveFile(savedBook.getId(), getUploadBooksDirectory(), part);
            savedBook.setImage(image);
            getBookService().updateWithImage(savedBook);
        }
    }

    private Book saveBook(Book book) {
        Translation title = getTranslationService().add(book.getTitle());
        Translation description = getTranslationService().add(book.getDescription());
        book.setTitle(title);
        book.setDescription(description);
        return getBookService().add(book);
    }

}
