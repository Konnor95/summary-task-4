package ua.nure.bekuzarov.SummaryTask4.filter;

import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.util.LocaleUtil;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Handles request and defines current locale.
 */
@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter extends BaseFilter {

    private static final String COOKIES_LOCALE = "lang";
    private static final String LOCALES_FILE = "/locales.properties";
    private Map<String, String> locales = new LinkedHashMap<>();
    private ServletContext servletContext;
    private String defaultLocale;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loadLocales(filterConfig);
        loadTranslator();
    }


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        String relativePath = path.replace(contextPath, "");
        String locale = getLocaleFromPath(relativePath);
        if (locale != null) {
            if (isDefault(locale)) {
                setLocale(request, response, locale);
                redirect(request, response, removeLocale(path, locale));
                return;
            }
            forward(locale, path, request, response, chain);
            return;
        }
        locale = getLocaleFromSession(request);
        if (isDefault(locale)) {
            forward(locale, path, request, response, chain);
            return;
        }
        redirect(request, response, contextPath + "/" + locale + relativePath);
    }

    private void loadLocales(FilterConfig filterConfig) {
        Tuple<Map<String, String>, String> tuple = LocaleUtil.loadLocales(LOCALES_FILE);
        locales = tuple.getX();
        defaultLocale = tuple.getY();
        servletContext = filterConfig.getServletContext();
        servletContext.setAttribute(Attributes.DEFAULT_LOCALE, defaultLocale);
        servletContext.setAttribute(Attributes.LOCALES, locales);
    }

    private void loadTranslator() {
        Translator translator = new Translator("/translations", locales.keySet());
        servletContext.setAttribute(Attributes.TRANSLATOR, translator);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        String query = getRequestQuery(request);
        response.sendRedirect(path + query);
    }

    private void forward(final String locale, String url, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String query = getRequestQuery(request);
        request.setAttribute(Attributes.QUERY, query);
        setLocale(request, response, locale);
        request.setAttribute(Attributes.URL, removeLocale(url, locale));
        chain.doFilter(request, response);
    }

    private String getLocaleFromPath(String relativePath) {
        for (String locale : locales.keySet()) {
            if (relativePath.startsWith("/" + locale)) {
                return locale;
            }
        }
        return null;
    }

    private String getLocaleFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = defineLocale((String) session.getAttribute(Attributes.CURRENT_LOCALE));
        return locale == null ? getLocaleFromCookies(request) : locale;
    }

    private String getLocaleFromCookies(HttpServletRequest request) {
        Cookie cookie = getCookie(request, COOKIES_LOCALE);
        String locale = defineLocale(cookie != null ? cookie.getValue() : null);
        return locale == null ? getLocaleFromRequest(request) : locale;
    }

    private String getLocaleFromRequest(HttpServletRequest request) {
        String locale = defineLocale(request.getLocale().getLanguage());
        return locale == null ? defaultLocale : locale;
    }

    private String defineLocale(String locale) {
        for (String l : locales.keySet()) {
            if (l.equals(locale)) {
                return l;
            }
        }
        return null;
    }

    private String getRequestQuery(HttpServletRequest request) {
        return request.getQueryString() != null ? "?" + request.getQueryString() : "";
    }

    private String removeLocale(String path, String locale) {
        return path.replace("/" + locale, "");
    }

    private boolean isDefault(String locale) {
        return defaultLocale.equals(locale);
    }

    private Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        setLocaleInSession(request, locale);
        setLocaleInCookies(response, locale);
    }

    private void setLocaleInSession(HttpServletRequest request, String locale) {
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.CURRENT_LOCALE, locale);
    }

    private void setLocaleInCookies(HttpServletResponse response, String locale) {
        Cookie cookie = new Cookie(COOKIES_LOCALE, locale);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
    }

}
