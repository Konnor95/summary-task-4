package ua.nure.bekuzarov.SummaryTask4.validator;

import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Validates {@link Book} object.
 */
public class BookValidator extends AbstractValidator {

    private static final String CANNOT_BE_EMPTY = "validate.cannotBeEmpty";
    private static final String CANNOT_BE_NEGATIVE = "validate.cannotBeNegative";

    /**
     * Creates a new validator that validates books immediately.
     *
     * @param book        book to validate
     * @param publisherId book publisher id
     * @param authorsIds  ids of the authors of the book
     * @param translator  translator object
     * @param locale      current locale
     * @param locales     map of all locales
     */
    public BookValidator(Book book, Integer publisherId, List<Integer> authorsIds, Translator translator, String locale, Map<String, String> locales) {
        super(translator, locale);
        if (book == null) {
            return;
        }
        Set<String> set = locales.keySet();
        validateTitle(book.getTitle(), set);
        validateDescription(book.getDescription(), set);
        put("amount", validateAmount(book.getAmount()));
        put("pages", validatePages(book.getPages()));
        put("year", validateYear(book.getYear()));
        put("publisherId", validatePublisher(publisherId));
        put("authors", validateAuthors(authorsIds));
    }

    private void validateTitle(Translation title, Set<String> locales) {
        for (String locale : locales) {
            put("title_" + locale, validateTitle(getTranslationValue(title, locale)));
        }
    }

    private String validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return CANNOT_BE_EMPTY;
        }
        if (title.length() < 2 || title.length() > 100) {
            return "validate.lengthFrom2to100";
        }
        return null;
    }

    private void validateDescription(Translation description, Set<String> locales) {
        for (String locale : locales) {
            put("description_" + locale, validateDescription(getTranslationValue(description, locale)));
        }
    }

    private String validateDescription(String description) {
        if (description == null) {
            return CANNOT_BE_EMPTY;
        }
        if (description.isEmpty()) {
            return null;
        }
        if (description.length() < 5 || description.length() > 1000) {
            return "validate.lengthFrom5to1000";
        }
        return null;
    }

    private String validateAmount(Integer amount) {
        if (amount == null) {
            return CANNOT_BE_EMPTY;
        }
        if (amount < 0) {
            return CANNOT_BE_NEGATIVE;
        }
        return null;
    }

    private String validatePages(Integer pages) {
        if (pages == null) {
            return CANNOT_BE_EMPTY;
        }
        if (pages < 1) {
            return "validate.pagesCount";
        }
        return null;
    }

    private String validateYear(Integer year) {
        if (year == null) {
            return CANNOT_BE_EMPTY;
        }
        if (year < 0) {
            return CANNOT_BE_NEGATIVE;
        }
        if (year > Calendar.getInstance().get(Calendar.YEAR)) {
            return "validate.year";
        }
        return null;
    }

    private String validatePublisher(Integer publisherId) {
        if (publisherId == null) {
            return CANNOT_BE_EMPTY;
        }
        if (publisherId < 0) {
            return CANNOT_BE_NEGATIVE;
        }
        return null;
    }

    private String validateAuthors(List<Integer> authorsIds) {
        if (authorsIds == null || authorsIds.size() == 0) {
            return CANNOT_BE_EMPTY;
        }
        for (Integer i : authorsIds) {
            if (i == null || i < 1) {
                return CANNOT_BE_NEGATIVE;
            }
        }
        return null;
    }

}
