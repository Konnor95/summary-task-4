package ua.nure.bekuzarov.SummaryTask4.servlet.library;

import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.servlet.BaseServlet;
import ua.nure.bekuzarov.SummaryTask4.util.ProcessResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Base servlet for all servlets of library module.
 */
public abstract class LibraryServlet extends BaseServlet {

    @Override
    protected String getUserAttributeName() {
        return Attributes.CURRENT_LIBRARIAN;
    }

    protected void getResult(HttpServletRequest req) {
        HttpSession session = req.getSession();
        ProcessResult result = (ProcessResult) session.getAttribute(Attributes.PROCESS_RESULT_LIBRARY);
        req.setAttribute(Attributes.PROCESS_RESULT, result);
        session.removeAttribute(Attributes.PROCESS_RESULT_LIBRARY);
    }

    protected void setResult(HttpServletRequest req, boolean succeeded, String message) {
        HttpSession session = req.getSession();
        ProcessResult result = new ProcessResult();
        result.setSucceeded(succeeded);
        result.setMessage(getTranslator().translate(message, getLocale(req)));
        session.setAttribute(Attributes.PROCESS_RESULT_LIBRARY, result);
    }

}
