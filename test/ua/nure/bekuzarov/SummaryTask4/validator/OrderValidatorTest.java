package ua.nure.bekuzarov.SummaryTask4.validator;


import org.junit.Test;
import ua.nure.bekuzarov.SummaryTask4.TranslationTest;

import static org.junit.Assert.assertEquals;
import static ua.nure.bekuzarov.SummaryTask4.util.Settings.getMaxCheckingOut;

public final class OrderValidatorTest extends TranslationTest {

    @Test
    public void test() {
        Validator validator = new OrderValidator(null, null, getTranslator(), getDefaultLocale());
        assertEquals(2, validator.getMessages().size());
        validator = new OrderValidator(-1, -1, getTranslator(), getDefaultLocale());
        assertEquals(2, validator.getMessages().size());
        validator = new OrderValidator(1, 1, getTranslator(), getDefaultLocale());
        assertEquals(0, validator.getMessages().size());
    }

    @Test
    public void testDays() {
        Validator validator = new OrderValidator(null, getTranslator(), getDefaultLocale());
        assertEquals(1, validator.getMessages().size());
        validator = new OrderValidator(-1, getTranslator(), getDefaultLocale());
        assertEquals(1, validator.getMessages().size());
        validator = new OrderValidator(getMaxCheckingOut() + 1, getTranslator(), getDefaultLocale());
        assertEquals(1, validator.getMessages().size());
        validator = new OrderValidator(getMaxCheckingOut() - 1, getTranslator(), getDefaultLocale());
        assertEquals(0, validator.getMessages().size());
    }

}
