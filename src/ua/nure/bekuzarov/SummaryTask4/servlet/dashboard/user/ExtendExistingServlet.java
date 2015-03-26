package ua.nure.bekuzarov.SummaryTask4.servlet.dashboard.user;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;

import javax.servlet.annotation.WebServlet;

/**
 * Responsible for extending subscriptions of users, whose subscriptions are not expired yet.
 */
@WebServlet(Actions.Dashboard.Users.EXTEND_EXISTING)
public class ExtendExistingServlet extends ExtendServlet {

    @Override
    protected String getRedirectAction() {
        return Actions.Dashboard.Users.LIST;
    }

    @Override
    protected String getUrl() {
        return Actions.Dashboard.Users.EXTEND_EXISTING;
    }
}
