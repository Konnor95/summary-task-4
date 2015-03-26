package ua.nure.bekuzarov.SummaryTask4.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base class for all filters. Handles {@link HttpServletRequest} only
 *
 * @author Dmitry Bekuzarov
 */
public abstract class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        doFilter(httpServletRequest, httpServletResponse, chain);
    }

    /**
     * Handles {@code HttpServletRequest} objects.
     *
     * @param request  request to handle
     * @param response response to return
     * @param chain    filter chain
     * @throws IOException
     * @throws ServletException
     */
    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    public void destroy() {

    }

}
