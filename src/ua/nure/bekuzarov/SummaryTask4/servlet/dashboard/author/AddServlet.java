package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.author;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.constant.Pages;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.util.Settings;
import ua.nure.bekuzarov.SummaryTask4.validator.AuthorValidator;
import ua.nure.bekuzarov.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getUploadAuthorsDirectory;
import static ua.nure.bekuzarov.SummaryTask4.util.Settings.isImage;

/**
 * Responsible for adding a new author.
 */
@WebServlet(Actions.Dashboard.Authors.ADD)
@MultipartConfig(
        location = Settings.Authors.TEMP_DIRECTORY,
        fileSizeThreshold = Settings.Authors.SIZE_THRESHOLD,
        maxFileSize = Settings.Authors.MAX_SIZE
)
public class AddServlet extends AuthorServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(getForwardPage(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = getAuthorFromRequest(req, resp, true);
        if (author == null) {
            return;
        }
        Validator validator = new AuthorValidator(author, getTranslator(), getLocale(req), getLocales());
        Part imagePart = req.getPart(Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && !isImage(imagePart.getContentType())) {
            validator.put(Attributes.IMAGE, "validate.invalidFileFormat");
        }

        if (validator.hasErrors()) {
            forwardBack(req, resp, validator, author);
            return;
        }

        saveAuthor(imagePart, author);
        setResult(req, true, "result.authorAdded");
        redirectToAction(Actions.Dashboard.Authors.LIST, req, resp);
    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Authors.ADD;
    }

    private void saveAuthor(Part part, Author author) {
        Author savedAuthor = saveAuthor(author);
        if (part != null && part.getSize() != 0) {
            String image = getFileService().saveFile(savedAuthor.getId(), getUploadAuthorsDirectory(), part);
            savedAuthor.setImage(image);
            getAuthorService().update(savedAuthor);
        }
    }

    private Author saveAuthor(Author author) {
        Translation name = getTranslationService().add(author.getName());
        Translation description = getTranslationService().add(author.getDescription());
        author.setName(name);
        author.setDescription(description);
        return getAuthorService().add(author);
    }


}
