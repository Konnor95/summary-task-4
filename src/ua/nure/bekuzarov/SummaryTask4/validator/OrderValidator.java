package ua.nure.bekuzarov.SummaryTask4.validator;

import ua.nure.bekuzarov.SummaryTask4.util.Translator;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getMaxCheckingOut;

/**
 * Validates order properties.
 *
 * @see ua.nure.bekuzarov.SummaryTask4.entity.Order
 * @see ua.nure.bekuzarov.SummaryTask4.entity.Book
 * @see ua.nure.bekuzarov.SummaryTask4.entity.Subscription
 */
public class OrderValidator extends AbstractValidator {

    /**
     * Creates a new validator that validates order days.
     *
     * @param days       days of the order
     * @param translator translator object
     * @param locale     current locale
     */
    public OrderValidator(Integer days, Translator translator, String locale) {
        super(translator, locale);
        put("days", validateDays(days));
    }

    /**
     * Creates a new validator that validates book id and subscription number
     *
     * @param bookId         id of the book
     * @param subscriptionId subscription number
     * @param translator     translator object
     * @param locale         current locale
     */
    public OrderValidator(Integer bookId, Integer subscriptionId, Translator translator, String locale) {
        super(translator, locale);
        put("bookId", validateId(bookId));
        put("subscriptionId", validateId(subscriptionId));
    }

    private String validateId(Integer id) {
        if (id == null) {
            return "validate.cannotBeEmpty";
        }
        if (id < 1) {
            return "validate.cannotBeNegative";
        }
        return null;
    }

    private String validateDays(Integer days) {
        if (days == null) {
            return "validate.cannotBeEmpty";
        }
        if (days < 1) {
            return "validate.cannotBeNegative";
        }
        if (days > getMaxCheckingOut()) {
            return "validate.tooBig";
        }
        return null;
    }

}

