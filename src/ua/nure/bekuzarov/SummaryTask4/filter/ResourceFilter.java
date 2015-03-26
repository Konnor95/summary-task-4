package ua.nure.bekuzarov.SummaryTask4.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.util.file.FileService;
import ua.nure.bekuzarov.SummaryTask4.util.file.FileServletUtil;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getUploadPathMapping;

/**
 * Filter requests for static resources (files, images etc.).
 */
@WebFilter(filterName = "ResourceFilter")
public class ResourceFilter extends BaseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceFilter.class);
    private FileService fileService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fileService = (FileService) filterConfig.getServletContext().getAttribute(Attributes.FILE_SERVICE);
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String path = req.getRequestURI();
        if (path.contains(getUploadPathMapping())) {
            returnFileFromStorage(req, resp);
            return;
        }
        path = path.replace(req.getContextPath(), "");
        req.getRequestDispatcher(URLDecoder.decode(path, "UTF-8")).forward(req, resp);
    }

    private void returnFileFromStorage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getServletPath();
        String filePath = path.replace(getUploadPathMapping(), "");
        File file = fileService.getFile(filePath);
        if (!file.exists()) {
            LOGGER.warn("File not found in {}", file.getAbsoluteFile());
            resp.sendError(404);
            return;
        }
        FileServletUtil.write(file, resp);
    }

}
