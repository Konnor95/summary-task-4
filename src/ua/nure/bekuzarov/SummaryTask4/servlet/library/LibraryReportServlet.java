package ua.nure.bekuzarov.SummaryTask4.servlet.library;

import ua.nure.bekuzarov.SummaryTask4.exception.FileException;
import ua.nure.bekuzarov.SummaryTask4.util.file.FileServletUtil;
import ua.nure.bekuzarov.SummaryTask4.util.report.Report;
import ua.nure.bekuzarov.SummaryTask4.util.report.ReportUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base servlet for all servlets that generate reports.
 */
public abstract class LibraryReportServlet extends LibraryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ReportUtil reportUtil = getUtil();
            Report report = reportUtil.generate(req.getPathInfo(), getLocale(req));
            resp.setContentType(report.getContentType());
            FileServletUtil.write(report.getFile(), resp);
        } catch (FileException e) {
            resp.sendError(404);
        }
    }

    protected abstract ReportUtil getUtil();

}
