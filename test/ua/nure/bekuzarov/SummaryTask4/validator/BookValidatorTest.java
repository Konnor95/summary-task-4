package ua.nure.bekuzarov.SummaryTask4.validator;

import org.junit.Test;
import ua.nure.bekuzarov.SummaryTask4.TranslationTest;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public final class BookValidatorTest extends TranslationTest {

    @Test
    public void testEmpty() {
        Book book = new Book();
        Validator validator = new BookValidator(book, null, null, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size() * 2 + 5, validator.getMessages().size());
        book.setTitle(new Translation("", ""));
        book.setDescription(new Translation("", ""));
        validator = new BookValidator(book, null, null, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size() + 5, validator.getMessages().size());
    }

    @Test
    public void testNull() {
        Validator validator = new BookValidator(null, null, null, getTranslator(), getDefaultLocale(), getLocales());
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testMaxLength() {
        Book book = new Book();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        book.setTitle(new Translation(name.toString(), name.toString()));
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 1001; i++) {
            description.append("a");
        }
        book.setDescription(new Translation(description.toString(), description.toString()));
        Validator validator = new BookValidator(book, null, null, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size() * 2 + 5, validator.getMessages().size());
    }

    @Test
    public void testMinLength() {
        Book book = new Book();
        book.setTitle(new Translation("a", "a"));
        book.setDescription(new Translation("aaa", "aaa"));
        Validator validator = new BookValidator(book, null, null, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size() * 2 + 5, validator.getMessages().size());
    }

    @Test
    public void testValid() {
        Book book = new Book();
        book.setTitle(new Translation("Ivan", "Ivan"));
        book.setDescription(new Translation("Lives in London", "Lives in London"));
        List<Integer> authors = Arrays.asList(1, 2);
        book.setYear(1999);
        book.setAmount(10);
        book.setPages(200);
        Validator validator = new BookValidator(book, 1, authors, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(0, validator.getMessages().size());
    }

    @Test
    public void testInvalid() {
        Book book = new Book();
        book.setTitle(new Translation("Ivan", "Ivan"));
        book.setDescription(new Translation("Lives in London", "Lives in London"));
        List<Integer> authors = Arrays.asList(-1);
        book.setYear(-1);
        book.setAmount(-1);
        book.setPages(-1);
        Validator validator = new BookValidator(book, -1, authors, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(5, validator.getMessages().size());
        book.setYear(Calendar.getInstance().get(Calendar.YEAR) + 1);
        validator = new BookValidator(book, -1, authors, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(5, validator.getMessages().size());
    }

}
