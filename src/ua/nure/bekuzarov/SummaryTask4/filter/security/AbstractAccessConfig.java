package ua.nure.bekuzarov.SummaryTask4.filter.security;

import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.entity.UserRole;

/**
 * Provides basic functionality of {@link AccessConfig} interface.
 */
public abstract class AbstractAccessConfig implements AccessConfig {

    private UserRole userRole;

    protected AbstractAccessConfig(UserRole userRole) {
        this.userRole = userRole;
    }

    protected boolean userRoleMatch(User... users) {
        if (users == null) {
            return false;
        }
        for (User user : users) {
            if (user != null && user.getRole() == userRole) {
                return true;
            }
        }
        return false;
    }
}
