package ua.nure.bekuzarov.SummaryTask4.servlet.library.orders.checkedOut;

import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.entity.Order;
import ua.nure.bekuzarov.SummaryTask4.entity.OrderType;
import ua.nure.bekuzarov.SummaryTask4.servlet.library.LibraryReportServlet;
import ua.nure.bekuzarov.SummaryTask4.util.file.FileService;
import ua.nure.bekuzarov.SummaryTask4.util.report.ReportUtil;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.Map;

/**
 * Responsible for generating report of checked out books.
 */
@WebServlet(Actions.Library.CheckedOut.REPORT)
public class ReportServlet extends LibraryReportServlet {

    @Override
    protected ReportUtil getUtil() {
        return new CheckedOutReportUtil(getFileService());
    }

    private final class CheckedOutReportUtil extends ReportUtil {

        private CheckedOutReportUtil(FileService fileService) {
            super(fileService);
        }

        @Override
        protected String getTemplateFileName() {
            return "orders";
        }

        @Override
        protected String getReportTitle(String locale) {
            return getTranslator().translate("text.orders.checkedOut", locale);
        }

        @Override
        protected void fillMetaData(FieldsMetadata metadata) {
            metadata.addFieldAsList("orders.Id");
            metadata.addFieldAsList("orders.BookId");
            metadata.addFieldAsList("orders.SubscriptionId");
        }

        @Override
        protected void fillHead(Map<String, String> head, String locale) {
            head.put("BookId", getTranslator().translate("text.book", locale));
            head.put("SubscriptionId", getTranslator().translate("text.subscription.id", locale));
        }

        @Override
        protected void fillBody(IContext context) {
            List<Order> orders = getOrderService().getAll(OrderType.CHECKED_OUT);
            context.put("orders", orders);
        }
    }

}
