package ua.nure.bekuzarov.SummaryTask4.validator;

import org.junit.Test;
import ua.nure.bekuzarov.SummaryTask4.TranslationTest;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;

import static org.junit.Assert.*;

public final class PublisherValidatorTest extends TranslationTest {

    @Test
    public void testEmpty() {
        Publisher publisher = new Publisher();
        Validator validator = new PublisherValidator(publisher, getTranslator(), getDefaultLocale(), getLocales());
        assertTrue(validator.hasErrors());
        publisher.setTitle(new Translation("", ""));
        validator = new PublisherValidator(publisher, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size(), validator.getMessages().size());
    }

    @Test
    public void testNull() {
        Validator validator = new PublisherValidator(null, getTranslator(), getDefaultLocale(), getLocales());
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testMaxLength() {
        Publisher publisher = new Publisher();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        publisher.setTitle(new Translation(name.toString(), name.toString()));
        Validator validator = new PublisherValidator(publisher, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size(), validator.getMessages().size());
    }

    @Test
    public void testMinLength() {
        Publisher publisher = new Publisher();
        publisher.setTitle(new Translation("a", "a"));
        Validator validator = new PublisherValidator(publisher, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size(), validator.getMessages().size());
    }

    @Test
    public void testValid() {
        Publisher publisher = new Publisher();
        publisher.setTitle(new Translation("Ivan", "Ivan"));
        Validator validator = new PublisherValidator(publisher, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(0, validator.getMessages().size());
    }

}