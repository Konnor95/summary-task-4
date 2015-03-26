package ua.nure.bekuzarov.SummaryTask4.validator;

import org.junit.Test;
import ua.nure.bekuzarov.SummaryTask4.TranslationTest;
import ua.nure.bekuzarov.SummaryTask4.entity.User;

import static org.junit.Assert.*;

public final class UserValidatorTest extends TranslationTest {

    @Test
    public void testNull() {
        Validator validator = new UserValidator(null, getTranslator(), getDefaultLocale());
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testEmail() {
        User user = new User();
        Validator validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("email"));
        user.setEmail("");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("email"));
        user.setEmail("dsdsd");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("email"));
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            email.append("a");
        }
        email.append("@gmail.com");
        user.setEmail(email.toString());
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("email"));
        user.setEmail("sample@gmail.com");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNull(validator.getMessages().get("email"));
    }

    @Test
    public void testFirstName() {
        User user = new User();
        Validator validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("firstName"));
        user.setFirstName("");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("firstName"));
        user.setFirstName("d");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("firstName"));
        user.setFirstName("123");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("firstName"));
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        user.setFirstName(name.toString());
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("firstName"));
        user.setFirstName("Ivan");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNull(validator.getMessages().get("firstName"));
    }


    @Test
    public void testLastName() {
        User user = new User();
        Validator validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("lastName"));
        user.setLastName("");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("lastName"));
        user.setLastName("d");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("lastName"));
        user.setLastName("123");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("lastName"));
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        user.setLastName(name.toString());
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("lastName"));
        user.setLastName("Ivan");
        validator = new UserValidator(user, getTranslator(), getDefaultLocale());
        assertNull(validator.getMessages().get("lastName"));
    }

    @Test
    public void validateLogin() {
        Validator validator = new UserValidator(null, null, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("login"));
        String login = "";
        validator = new UserValidator(login, null, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("login"));
        login = "d";
        validator = new UserValidator(login, null, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("login"));
        StringBuilder loginString = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            loginString.append("a");
        }
        validator = new UserValidator(loginString.toString(), null, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("login"));
        login = "John 95";
        validator = new UserValidator(login, null, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("login"));
        login = "John95";
        validator = new UserValidator(login, null, getTranslator(), getDefaultLocale());
        assertNull(validator.getMessages().get("login"));
    }

    @Test
    public void testPassword() {
        Validator validator = new UserValidator(null, null, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("password"));
        String password = "";
        validator = new UserValidator(null, password, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("password"));
        password = "ddd";
        validator = new UserValidator(null, password, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("password"));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            s.append("a");
        }
        validator = new UserValidator(null, s.toString(), getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("password"));
        password = "John Doe";
        validator = new UserValidator(null, password, getTranslator(), getDefaultLocale());
        assertNotNull(validator.getMessages().get("password"));
        password = "John95";
        validator = new UserValidator(null, password, getTranslator(), getDefaultLocale());
        assertNull(validator.getMessages().get("password"));
    }
}
