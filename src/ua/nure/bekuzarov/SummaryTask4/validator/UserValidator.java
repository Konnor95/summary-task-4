package ua.nure.bekuzarov.SummaryTask4.validator;

import ua.nure.bekuzarov.SummaryTask4.entity.User;
import ua.nure.bekuzarov.SummaryTask4.util.Translator;

import java.util.regex.Pattern;

/**
 * Validates {@link User} object.
 */
public class UserValidator extends AbstractValidator {

    private static final String CANNOT_BE_EMPTY = "validate.cannotBeEmpty";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern LOGIN_PATTERN = Pattern.compile(
            "^[a-z0-9_-]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^\\p{L}+$", Pattern.CASE_INSENSITIVE);


    /**
     * Creates a new validator that validates {@code user} immediately.
     *
     * @param user       user to validate
     * @param translator translator object
     * @param locale     current locale
     */
    public UserValidator(User user, Translator translator, String locale) {
        super(translator, locale);
        if (user == null) {
            return;
        }
        put("firstName", validateName(user.getFirstName()));
        put("lastName", validateName(user.getLastName()));
        put("email", validateEmail(user.getEmail()));
        put("login", validateLogin(user.getLogin()));
        put("password", validatePassword(user.getPassword()));
    }

    /**
     * Creates a new validator that validates user's login and password immediately.
     *
     * @param login      user's login
     * @param password   user's password
     * @param translator translator object
     * @param locale     current locale
     */
    public UserValidator(String login, String password, Translator translator, String locale) {
        super(translator, locale);
        put("login", validateLogin(login));
        put("password", validatePassword(password));
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return CANNOT_BE_EMPTY;
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            return "validate.lettersOnly";
        }
        if (name.length() < 2 || name.length() > 100) {
            return "validate.lengthFrom2to100";
        }
        return null;
    }


    private String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return CANNOT_BE_EMPTY;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "validate.invalidEmail";
        }
        if (email.length() < 2 || email.length() > 100) {
            return "validate.lengthFrom2to100";
        }
        return null;
    }

    private String validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            return CANNOT_BE_EMPTY;
        }
        if (!LOGIN_PATTERN.matcher(login).matches()) {
            return "validate.loginPatternMatch";
        }
        if (login.length() < 5 || login.length() > 100) {
            return "validate.lengthFrom5to100";
        }
        return null;
    }

    private String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return CANNOT_BE_EMPTY;
        }
        if (password.contains(" ")) {
            return "validate.noWhitespaces";
        }
        if (password.length() < 5 || password.length() > 100) {
            return "validate.lengthFrom5to100";
        }
        return null;
    }

}
