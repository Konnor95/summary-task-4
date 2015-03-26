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
 * Responsible for book editing.
 */
@WebServlet(Actions.Dashboard.Books.EDIT)
@MultipartConfig(
        location = Settings.Books.TEMP_DIRECTORY,
        fileSizeThreshold = Settings.Books.SIZE_THRESHOLD,
        maxFileSize = Settings.Books.MAX_SIZE
)
public class EditServlet extends BookServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBook(req, resp);
        if (book == null) {
            return;
        }
        req.setAttribute(Attributes.BOOK, book);
        setSelect(req);
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBookFromRequest(req, resp, false);
        if (book == null) {
            return;
        }
        Book existingBook = getBookService().getById(book.getId());
        if (existingBook == null) {
            redirectBack(req, resp);
            return;
        }
        Integer publisherId = getIntParam(req, "publisherId");
        List<Integer> authorsIds = getIntParamValues(req, "authors");
        Validator validator = new BookValidator(
                book, publisherId, authorsIds, getTranslator(), getLocale(req), getLocales()
        );
        Part imagePart = req.getPart(Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && !isImage(imagePart.getContentType())) {
            validator.put(Attributes.IMAGE, "validate.invalidFileFormat");
        }

        book.setPublisher(getPublisherService().getById(publisherId));
        book.setAuthors(getAuthorService().getAll(authorsIds));
        if (book.getAmount() != null && book.getAmount() < existingBook.getAmount() - existingBook.getInStock()) {
            validator.put("amount", "validate.bookAmount");
        }
        if (validator.hasErrors()) {
            book.setImage(existingBook.getImage());
            forwardBack(req, resp, validator, book);
            return;
        }
        book.setInStock(existingBook.getInStock() + (book.getAmount() - existingBook.getAmount()));
        updateBook(req, existingBook, book, imagePart);
        setResult(req, true, "result.bookEdited");
        redirectToAction(Actions.Dashboard.Books.LIST, req, resp);

    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Books.EDIT;
    }

    private void updateBook(HttpServletRequest req, Book existingBook, Book book, Part part) {
        Boolean deleteImage = Boolean.parseBoolean(req.getParameter("delete-" + Attributes.IMAGE));
        if (part == null || part.getSize() == 0) {
            if (deleteImage) {
                setImage(book, null);
                updateBook(existingBook, book, true);
                return;
            }
            updateBook(existingBook, book, false);
            return;
        }

        String image = getFileService().saveFile(existingBook.getId(), getUploadBooksDirectory(), part);
        setImage(book, image);
        updateBook(existingBook, book, true);

    }

    private void updateBook(Book existingBook, Book book, boolean updateImage) {
        Translation title = existingBook.getTitle();
        Translation description = existingBook.getDescription();
        title.clone(book.getTitle());
        description.clone(book.getDescription());
        getTranslationService().update(title);
        getTranslationService().update(description);
        if (updateImage) {
            getBookService().updateWithImage(book);
        } else {
            getBookService().update(book);
        }
    }

    private void setImage(Book book, String image) {
        book.setImage(image);
    }
}
