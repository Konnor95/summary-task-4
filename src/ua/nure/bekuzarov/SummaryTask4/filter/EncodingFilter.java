package ua.nure.bekuzarov.SummaryTask4.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Set request charset encoding to specific encoding.
 *
 * @author Dmitry Bekuzarov
 */
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter extends BaseFilter {

    private static final String ENCODING = "UTF-8";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        chain.doFilter(request, response);
    }

}
