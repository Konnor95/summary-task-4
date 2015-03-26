package ua.nure.bekuzarov.SummaryTask4.validator;

import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all entity validators.
 */
public abstract class AbstractValidator implements Validator {

    private Translator translator;
    private Map<String, String> messages;
    private String locale;

    protected AbstractValidator(Translator translator, String locale) {
        this.translator = translator;
        this.locale = locale;
        messages = new HashMap<>();
    }

    @Override
    public boolean hasErrors() {
        return messages.size() != 0;
    }

    @Override
    public Map<String, String> getMessages() {
        return messages;
    }

    @Override
    public void put(String key, String message) {
        if (key == null || message == null) {
            return;
        }
        messages.put(key, translator.translate(message, locale));
    }

    protected String getTranslationValue(Translation translation, String locale) {
        return translation == null ? null : translation.value(locale);
    }

}
