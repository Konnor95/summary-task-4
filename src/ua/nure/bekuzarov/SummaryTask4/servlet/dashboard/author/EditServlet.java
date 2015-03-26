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
 * Responsible for author editing.
 */
@WebServlet(Actions.Dashboard.Authors.EDIT)
@MultipartConfig(
        location = Settings.Authors.TEMP_DIRECTORY,
        fileSizeThreshold = Settings.Authors.SIZE_THRESHOLD,
        maxFileSize = Settings.Authors.MAX_SIZE
)
public class EditServlet extends AuthorServlet {

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
        Author author = getAuthorFromRequest(req, resp, false);
        if (author == null) {
            return;
        }

        Author existingAuthor = getAuthorService().getById(author.getId());
        if (existingAuthor == null) {
            redirectBack(req, resp);
            return;
        }

        Validator validator = new AuthorValidator(author, getTranslator(), getLocale(req), getLocales());
        Part imagePart = req.getPart(Attributes.IMAGE);

        if (imagePart != null && imagePart.getSize() != 0 && !isImage(imagePart.getContentType())) {
            validator.put(Attributes.IMAGE, "validate.invalidFileFormat");
        }

        if (validator.hasErrors()) {
            author.setImage(existingAuthor.getImage());
            forwardBack(req, resp, validator, author);
            return;
        }

        updateAuthor(req, existingAuthor, author, imagePart);
        setResult(req, true, "result.authorEdited");
        redirectToAction(Actions.Dashboard.Authors.LIST, req, resp);

    }

    @Override
    protected String getForwardPage() {
        return Pages.Dashboard.Authors.EDIT;
    }

    private void updateAuthor(HttpServletRequest req, Author existingAuthor, Author author, Part part) {
        Boolean deleteImage = Boolean.parseBoolean(req.getParameter("delete-" + Attributes.IMAGE));
        updateAuthor(existingAuthor, author);
        if (part == null || part.getSize() == 0) {
            if (deleteImage) {
                setImage(existingAuthor, null);
                return;
            }
            return;
        }

        String image = getFileService().saveFile(existingAuthor.getId(), getUploadAuthorsDirectory(), part);
        setImage(existingAuthor, image);
    }

    private void updateAuthor(Author existingAuthor, Author author) {
        Translation name = existingAuthor.getName();
        Translation description = existingAuthor.getDescription();
        name.setEn(author.getName().getEn());
        name.setRu(author.getName().getRu());
        description.setEn(author.getDescription().getEn());
        description.setRu(author.getDescription().getRu());
        getTranslationService().update(name);
        getTranslationService().update(description);
    }

    private void setImage(Author author, String image) {
        author.setImage(image);
        getAuthorService().update(author);
    }
}
