package ua.nure.bekuzarov.SummaryTask4.filter.security;

import ua.nure.bekuzarov.SummaryTask4.constant.Actions;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;
import ua.nure.bekuzarov.SummaryTask4.filter.BaseFilter;
import ua.nure.bekuzarov.SummaryTask4.util.LocaleUtil;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filters request and defines whether the current user has access to the action.
 * In case of denied access redirects to the appropriate action.
 */
@WebFilter(filterName = "AccessFilter")
public class AccessFilter extends BaseFilter {

    private static final AccessConfig[] CONFIGS = new AccessConfig[]{
            new ActionAccessConfig(
                    UserRole.USER,
                    Actions.Main.LOGIN,
                    Actions.Main.PROFILE,
                    Actions.Main.Cart.CART
            ),
            new ModuleAccessConfig(
                    UserRole.ADMIN,
                    Actions.Dashboard.INDEX,
                    Actions.Dashboard.LOGIN,
                    Actions.Dashboard.LOGIN
            ),
            new ModuleAccessConfig(
                    UserRole.LIBRARIAN,
                    Actions.Library.INDEX,
                    Actions.Library.LOGIN,
                    Actions.Library.LOGIN
            )
    };
    private String defaultLocale;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (defaultLocale == null) {
            defaultLocale = LocaleUtil.getDefaultLocale(request.getServletContext());
        }
        HttpSession session = request.getSession();
        String path = (String) request.getAttribute(Attributes.URL);
        path = path.replace(request.getContextPath(), "");
        User admin = (User) session.getAttribute(Attributes.CURRENT_ADMIN);
        User librarian = (User) session.getAttribute(Attributes.CURRENT_LIBRARIAN);
        User user = (User) session.getAttribute(Attributes.CURRENT_USER);
        String redirect = isAllowed(path, admin, librarian, user);
        if (redirect != null) {
            response.sendRedirect(LocaleUtil.renderPath(redirect, request, defaultLocale));
            return;
        }
        chain.doFilter(request, response);
    }

    private String isAllowed(String path, User... users) {
        for (AccessConfig config : CONFIGS) {
            Tuple<Boolean, Boolean> t = config.isAllowed(path, users);
            if (t.getY()) {
                continue;
            }
            return t.getX() ? null : config.getRedirect();
        }
        return null;
    }

}
