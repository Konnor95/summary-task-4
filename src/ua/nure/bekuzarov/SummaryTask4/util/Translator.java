package ua.nure.bekuzarov.SummaryTask4.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * A util for translation.
 */
public final class Translator {

    private Map<String, ResourceBundle> resourceBundles;

    /**
     * Creates a new translator.
     *
     * @param bundlePath path to bundle of {@code *.properties} files that contains translations.
     * @param locales    a collection of locales
     */
    public Translator(String bundlePath, Iterable<String> locales) {
        resourceBundles = new HashMap<>();
        for (String locale : locales) {
            ResourceBundle bundle = ResourceBundle.getBundle(bundlePath, new Locale(locale));
            resourceBundles.put(locale, bundle);
        }
    }

    /**
     * Gets a translation for the specified {@code key} .
     *
     * @param key    key to look for
     * @param locale current locale
     * @return found translation
     */
    public String translate(String key, String locale) {
        ResourceBundle bundle = resourceBundles.get(locale);
        if (bundle == null) {
            return null;
        }
        return bundle.getString(key);
    }

}
