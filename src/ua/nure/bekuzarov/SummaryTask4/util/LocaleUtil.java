package ua.nure.bekuzarov.SummaryTask4.util;

import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.exception.FileException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Util class for internationalization purposes.
 */
public final class LocaleUtil {

    /**
     * Load locales from {@code *.properties} file.
     * @param file path to {@code *.properties} file
     * @return tuple (first element - locales collection, second - default locale)
     */
    public static Tuple<Map<String, String>, String> loadLocales(final String file) {
        Properties properties = new Properties();
        String defaultLocale = null;
        Map<String, String> locales = new LinkedHashMap<>();
        try (InputStream resource = LocaleUtil.class.getResourceAsStream(file)) {
            properties.load(resource);
        } catch (Exception e) {
            throw new FileException("Cannot load file: '" + file + "'", e);
        }
        for (int i = 1; ; i++) {
            String name = "locale" + i;
            String key = properties.getProperty(name + ".key");
            String value = properties.getProperty(name + ".value");
            if (key == null || value == null) {
                break;
            }
            if (i == 1) {
                defaultLocale = key;
            }
            locales.put(key, value);
        }
        return new Tuple<>(locales, defaultLocale);
    }

    /**
     * Gets default locale from {@code context}
     * @param context servlet context
     * @return default locale from {@code context}
     */
    public static String getDefaultLocale(ServletContext context) {
        return (String) context.getAttribute(Attributes.DEFAULT_LOCALE);
    }

    /**
     * Gets current locale from {@code request}
     * @param request current request
     * @return current locale from {@code request}
     */
    public static String getLocale(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(Attributes.CURRENT_LOCALE);
    }

    /**
     * Renders relative path to specified URI
     * @param uri URI that is used to render path
     * @param req current request
     * @param defaultLocale default locale
     * @return relative path to specified URI
     */
    public static String renderPath(String uri, HttpServletRequest req, String defaultLocale) {
        String locale = getLocale(req);
        locale = isDefault(defaultLocale, locale) ? "" : "/" + locale;
        return req.getContextPath() + locale + uri;
    }

    /**
     * Renders absolute path to specified URI
     * @param uri URI that is used to render path
     * @param req current request
     * @param defaultLocale default locale
     * @return absolute path to specified URI
     */
    public static String renderAbsolutePath(String uri, HttpServletRequest req, String defaultLocale) {
        return getSiteURL(req) + renderPath(uri, req, defaultLocale);
    }

    private static String getSiteURL(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.substring(0, url.length() - request.getRequestURI().length());
    }

    private static boolean isDefault(String defaultLocale, String locale) {
        return defaultLocale.equals(locale);
    }

    private LocaleUtil() {
    }

}
