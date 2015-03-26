package ua.nure.bekuzarov.SummaryTask4;


import org.junit.Test;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Entity;
import ua.nure.bekuzarov.SummaryTask4.exception.FileException;
import ua.nure.bekuzarov.SummaryTask4.util.Functions;
import ua.nure.bekuzarov.SummaryTask4.util.LocaleUtil;
import ua.nure.bekuzarov.SummaryTask4.util.ProcessResult;
import ua.nure.bekuzarov.SummaryTask4.util.Tuple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testTuple() {
        Tuple<Integer, String> tuple = new Tuple<>(1, "value");
        assertEquals(1, (int) tuple.getX());
        assertEquals("value", tuple.getY());
    }

    @Test
    public void testFunctionContains() {
        Book book = new Book();
        book.setId(1);
        Author author = new Author();
        author.setId(2);
        List<Entity> values = Arrays.asList(book, author);
        Book book1 = new Book();
        book1.setId(1);
        Author author2 = new Author();
        author2.setId(3);
        assertTrue(Functions.contains(values, book1));
        assertFalse(Functions.contains(values, author2));
    }

    @Test
    public void testFunctionNumberGroup() {
        assertEquals(1, Functions.getNumberGroup(0));
        assertEquals(2, Functions.getNumberGroup(21));
        assertEquals(1, Functions.getNumberGroup(6));
        assertEquals(1, Functions.getNumberGroup(10));
        assertEquals(1, Functions.getNumberGroup(12));
        assertEquals(3, Functions.getNumberGroup(22));
    }

    @Test
    public void testFunctionsConstructor() throws ReflectiveOperationException {
        Constructor constructor = Functions.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testProcessResult() {
        ProcessResult processResult = new ProcessResult();
        processResult.setMessage("message");
        processResult.setSucceeded(true);
        assertEquals("message", processResult.getMessage());
        assertEquals(true, processResult.isSucceeded());
    }

    @Test(expected = FileException.class)
    public void testNotExistingFile() {
        LocaleUtil.loadLocales("someNotExistingPath 123");
    }

}
