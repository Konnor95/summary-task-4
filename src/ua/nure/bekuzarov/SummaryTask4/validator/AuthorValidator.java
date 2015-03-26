package ua.nure.bekuzarov.SummaryTask4.validator;

import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;

import java.util.Map;
import java.util.Set;

/**
 * Validates {@link Author} object.
 */
public class AuthorValidator extends AbstractValidator {

    /**
     * Creates a new validator that validates {@code author} immediately.
     *
     * @param author     author to validate
     * @param translator translator object
     * @param locale     current locale
     * @param locales    map of all locales
     */
    public AuthorValidator(Author author, Translator translator, String locale, Map<String, String> locales) {
        super(translator, locale);
        if (author == null) {
            return;
        }
        Set<String> keys = locales.keySet();
        validateName(author.getName(), keys);
        validateDescription(author.getDescription(), keys);
    }

    private void validateName(Translation name, Set<String> locales) {
        for (String locale : locales) {
            put("name_" + locale, validateName(getTranslationValue(name, locale)));
        }
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return "validate.cannotBeEmpty";
        }
        if (name.length() < 2 || name.length() > 100) {
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
            return "validate.cannotBeEmpty";
        }
        if (description.isEmpty()) {
            return null;
        }
        if (description.length() < 5 || description.length() > 1000) {
            return "validate.lengthFrom5to1000";
        }
        return null;
    }

}
