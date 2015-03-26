package ua.nure.bekuzarov.SummaryTask4.filter;

import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The last filter in chain. Handles request and passes it to servlet.
 */
@WebFilter(filterName = "ForwardFilter")
public class ForwardFilter extends BaseFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = (String) request.getAttribute(Attributes.URL);
        path = path.replace(request.getContextPath(), "");
        request.setAttribute("partialUrl", path + request.getAttribute(Attributes.QUERY));
        request.getRequestDispatcher(path).forward(request, response);
    }

}
