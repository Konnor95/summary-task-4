package ua.nure.bekuzarov.SummaryTask4.validator;


import org.junit.Test;
import ua.nure.bekuzarov.SummaryTask4.TranslationTest;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;

import static org.junit.Assert.*;

public final class AuthorValidatorTest extends TranslationTest {

    @Test
    public void testEmpty() {
        Author author = new Author();
        Validator validator = new AuthorValidator(author, getTranslator(), getDefaultLocale(), getLocales());
        assertTrue(validator.hasErrors());
        author.setName(new Translation("", ""));
        author.setDescription(new Translation("", ""));
        validator = new AuthorValidator(author, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size(), validator.getMessages().size());
    }

    @Test
    public void testNull() {
        Validator validator = new AuthorValidator(null, getTranslator(), getDefaultLocale(), getLocales());
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testMaxLength() {
        Author author = new Author();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        author.setName(new Translation(name.toString(), name.toString()));
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 1001; i++) {
            description.append("a");
        }
        author.setDescription(new Translation(description.toString(), description.toString()));
        Validator validator = new AuthorValidator(author, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size() * 2, validator.getMessages().size());
    }

    @Test
    public void testMinLength() {
        Author author = new Author();
        author.setName(new Translation("a", "a"));
        author.setDescription(new Translation("aaa", "aaa"));
        Validator validator = new AuthorValidator(author, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(new Translation().values().size() * 2, validator.getMessages().size());
    }

    @Test
    public void testValid() {
        Author author = new Author();
        author.setName(new Translation("Ivan", "Ivan"));
        author.setDescription(new Translation("Lives in London", "Lives in London"));
        Validator validator = new AuthorValidator(author, getTranslator(), getDefaultLocale(), getLocales());
        assertEquals(0, validator.getMessages().size());
    }

}
