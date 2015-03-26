package ua.nure.bekuzarov.SummaryTask4.servlet;

import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.service.author.AuthorService;
import ua.nure.bekuzarov.SummaryTask4.service.author.AuthorServiceImpl;
import ua.nure.bekuzarov.SummaryTask4.service.book.BookService;
import ua.nure.bekuzarov.SummaryTask4.service.book.BookServiceImpl;
import ua.nure.bekuzarov.SummaryTask4.service.order.OrderService;
import ua.nure.bekuzarov.SummaryTask4.service.order.OrderServiceImpl;
import ua.nure.bekuzarov.SummaryTask4.service.publisher.PublisherService;
import ua.nure.bekuzarov.SummaryTask4.service.publisher.PublisherServiceImpl;
import ua.nure.bekuzarov.SummaryTask4.service.translation.TranslationService;
import ua.nure.bekuzarov.SummaryTask4.service.translation.TranslationServiceImpl;
import ua.nure.bekuzarov.SummaryTask4.service.user.UserService;
import ua.nure.bekuzarov.SummaryTask4.service.user.UserServiceImpl;
import ua.nure.bekuzarov.SummaryTask4.util.LocaleUtil;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A base servlet for all servlets. Provides access to services.
 */
public abstract class AbstractServlet extends HttpServlet {

    private UserService userService;
    private AuthorService authorService;
    private TranslationService translationService;
    private OrderService orderService;
    private BookService bookService;
    private PublisherService publisherService;
    private Translator translator;
    private String defaultLocale;
    private Map<String, String> locales;

    @Override
    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute(UserServiceImpl.class.getName());
        authorService = (AuthorService) context.getAttribute(AuthorServiceImpl.class.getName());
        translationService = (TranslationService) context.getAttribute(TranslationServiceImpl.class.getName());
        orderService = (OrderService) context.getAttribute(OrderServiceImpl.class.getName());
        bookService = (BookService) context.getAttribute(BookServiceImpl.class.getName());
        publisherService = (PublisherService) context.getAttribute(PublisherServiceImpl.class.getName());
        translator = (Translator) context.getAttribute(Attributes.TRANSLATOR);
        defaultLocale = (String) context.getAttribute(Attributes.DEFAULT_LOCALE);
        locales = (Map<String, String>) context.getAttribute(Attributes.LOCALES);
    }

    protected UserService getUserService() {
        return userService;
    }

    protected AuthorService getAuthorService() {
        return authorService;
    }

    protected TranslationService getTranslationService() {
        return translationService;
    }

    protected OrderService getOrderService() {
        return orderService;
    }

    protected BookService getBookService() {
        return bookService;
    }

    protected PublisherService getPublisherService() {
        return publisherService;
    }

    protected Translator getTranslator() {
        return translator;
    }

    protected String getDefaultLocale() {
        return defaultLocale;
    }

    protected Map<String, String> getLocales() {
        return locales;
    }

    protected String getLocale(HttpServletRequest request) {
        return LocaleUtil.getLocale(request);
    }

    protected Integer getIntParam(HttpServletRequest req, String paramName) {
        String param = req.getParameter(paramName);
        if (param == null) {
            return null;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected int getIntParam(HttpServletRequest req, String paramName, int defaultValue) {
        Integer value = getIntParam(req, paramName);
        if (value == null || value < 1) {
            return defaultValue;
        }
        return value;
    }

    protected List<Integer> getIntParamValues(HttpServletRequest req, String paramName) {
        String[] values = req.getParameterValues(paramName);
        if (values == null) {
            return null;
        }
        List<Integer> integerValues = new ArrayList<>();
        for (String value : values) {
            try {
                Integer i = Integer.parseInt(value);
                integerValues.add(i);
            } catch (NumberFormatException e) {
                integerValues.add(null);
            }
        }
        return integerValues;
    }

    protected String getStringParam(HttpServletRequest req, String paramName) {
        String param = req.getParameter(paramName);
        if (param == null) {
            return null;
        }
        return param.trim();
    }


    protected String getStringParam(HttpServletRequest req, String paramName, String defaultValue) {
        String value = getStringParam(req, paramName);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return value;
    }

}
