package ua.nure.bekuzarov.SummaryTask4;

import ua.nure.bekuzarov.SummaryTask4.util.LocaleUtil;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import java.util.Map;

public abstract class TranslationTest {

    private static final String LOCALES_FILE = "/locales.properties";
    private Translator translator;
    private Map<String, String> locales;
    private String defaultLocale;

    protected TranslationTest() {
        Tuple<Map<String, String>, String> tuple = LocaleUtil.loadLocales(LOCALES_FILE);
        locales = tuple.getX();
        defaultLocale = tuple.getY();
        translator = new Translator("translations", locales.keySet());
    }

    protected Map<String, String> getLocales() {
        return locales;
    }

    protected String getDefaultLocale() {
        return defaultLocale;
    }

    public Translator getTranslator() {
        return translator;
    }
}
