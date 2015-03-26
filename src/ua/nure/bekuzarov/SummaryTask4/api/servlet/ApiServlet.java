package ua.nure.bekuzarov.SummaryTask4.api.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.api.serialization.StreamSerializer;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.exception.SerializerException;
import ua.nure.bekuzarov.SummaryTask4.servlet.AbstractServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A base servlet for API servlets.
 */
public abstract class ApiServlet extends AbstractServlet {

    private static final Pattern ID_PATTERN = Pattern.compile("/([0-9]*)");
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServlet.class);

    protected Integer getId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            resp.sendError(404);
            return null;
        }
        Matcher matcher = ID_PATTERN.matcher(pathInfo);
        Integer id = null;
        if (matcher.find()) {
            try {
                id = Integer.parseInt(matcher.group().substring(1));
            } catch (NumberFormatException e) {
                id = null;
            }
        }
        if (id == null) {
            resp.sendError(404);
            return null;
        }
        return id;
    }

    protected void print(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException {
        StreamSerializer serializer = (StreamSerializer) request.getAttribute(Attributes.SERIALIZER);
        if (serializer == null) {
            response.sendError(404);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType(serializer.getContentType());
        try {
            serializer.serialize(response.getOutputStream(), o);
        } catch (SerializerException e) {
            LOGGER.warn("Cannot serialize object", e);
            response.setContentType("text/html");
            response.sendError(500);
        }

    }

    /**
     * Prints a list of items to response
     *
     * @param request  request
     * @param response response
     * @param list     of items to print
     * @param c        class of items stored in the list
     * @param <T>      type of items stored in the list
     * @throws IOException if an input or output exception occurred
     */
    protected <T> void print(HttpServletRequest request, HttpServletResponse response, List<T> list, Class<T> c) throws IOException {
        StreamSerializer serializer = (StreamSerializer) request.getAttribute(Attributes.SERIALIZER);
        if (serializer == null) {
            response.sendError(404);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType(serializer.getContentType());
        try {
            serializer.serializeList(response.getOutputStream(), list, c);
        } catch (SerializerException e) {
            LOGGER.warn("Cannot serialize list of objects", e);
            response.setContentType("text/html");
            response.sendError(500);
        }
    }

}
