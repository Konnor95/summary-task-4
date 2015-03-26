package ua.nure.bekuzarov.SummaryTask4.filter;

import ua.nure.bekuzarov.SummaryTask4.api.serialization.StreamSerializer;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Filters requests to API.
 */
@WebFilter(filterName = "ApiFilter")
public class ApiFilter extends BaseFilter {

    private Map<String, StreamSerializer> serializers;

    @Override
    @SuppressWarnings("unchecked")
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        serializers = (Map<String, StreamSerializer>) context.getAttribute(Attributes.SERIALIZERS);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            response.sendError(404);
            return;
        }
        path = path.replace(request.getContextPath(), "");
        for (String key : serializers.keySet()) {
            String s = "/" + key;
            if (path.contains(s)) {
                path = path.replace(s, "");
                setSerializer(request, key);
                request.getRequestDispatcher(path).forward(request, response);
                return;
            }
        }
        response.sendError(404);
    }

    private void setSerializer(HttpServletRequest request, String key) {
        request.setAttribute(Attributes.SERIALIZER, serializers.get(key));
    }

}
