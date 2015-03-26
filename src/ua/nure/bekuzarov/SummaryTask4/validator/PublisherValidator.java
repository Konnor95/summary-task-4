package ua.nure.bekuzarov.SummaryTask4.validator;

import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;

import java.util.Map;
import java.util.Set;

/**
 * Validates {@link Publisher} object.
 */
public class PublisherValidator extends AbstractValidator {

    /**
     * Creates a new validator that validates {@code publisher} immediately.
     * @param publisher publisher to validate
     * @param translator translator object
     * @param locale current locale
     * @param locales map of all locales
     */
    public PublisherValidator(Publisher publisher, Translator translator, String locale, Map<String, String> locales) {
        super(translator, locale);
        if (publisher == null) {
            return;
        }
        validateTitle(publisher.getTitle(), locales.keySet());
    }

    private void validateTitle(Translation title, Set<String> locales) {
        for (String locale : locales) {
            put("title_" + locale, validateTitle(getTranslationValue(title, locale)));
        }
    }

    private String validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return "validate.cannotBeEmpty";
        }
        if (title.length() < 2 || title.length() > 100) {
            return "validate.lengthFrom2to100";
        }
        return null;
    }

}
