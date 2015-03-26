package ua.nure.bekuzarov.SummaryTask4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.nure.bekuzarov.SummaryTask4.api.serialization.JSONSerializer;
import ua.nure.bekuzarov.SummaryTask4.api.serialization.StreamSerializer;
import ua.nure.bekuzarov.SummaryTask4.api.serialization.XMLSerializer;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;
import ua.nure.bekuzarov.SummaryTask4.entity.Book;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;
import ua.nure.bekuzarov.SummaryTask4.exception.SerializerException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class SerializationTest {

    @Parameterized.Parameters
    public static Collection<StreamSerializer[]> data() {
        return Arrays.asList(
                new StreamSerializer[]{new JSONSerializer()},
                new StreamSerializer[]{new XMLSerializer()}
        );
    }

    private final StreamSerializer serializer;

    public SerializationTest(StreamSerializer serializer) {
        this.serializer = serializer;
    }

    @Test
    public void test() {
        Book book = new Book();
        book.setId(4);
        book.setPages(843);
        book.setAmount(10);
        book.setInStock(4);
        book.setYear(1997);
        Author author1 = new Author();
        author1.setId(5);
        author1.setImage("imagepath1");
        author1.setName(new Translation("Title", "Title"));
        author1.setDescription(new Translation("Description", "Description"));
        Author author2 = new Author();
        author2.setId(5);
        author2.setImage("imagepath2");
        author2.setName(new Translation("Title", "Title"));
        author2.setDescription(new Translation("Description", "Description"));
        book.setAuthors(Arrays.asList(author1, author2));
        book.setTitle(new Translation("Title", "Title"));
        book.setDescription(new Translation("Description", "Description"));
        Publisher publisher = new Publisher();
        publisher.setId(8);
        publisher.setTitle(new Translation("title", "title"));
        book.setPublisher(publisher);
        String s = serialize(book);
        Book book2 = deserialize(s, Book.class);
        List<Book> books = Arrays.asList(book, book2);
        String bs = serialize(books, Book.class);
        deserializeList(bs, Book.class);
    }

    @Test(expected = SerializerException.class)
    public void testInvalidSerialization() {
        serialize(new Object());
    }

    @Test(expected = SerializerException.class)
    public void testInvalidListSerialization() {
        serialize(Arrays.asList(new Object()), Object.class);
    }

    @Test(expected = SerializerException.class)
    public void testInvalidDeserialization() {
        deserialize("", Object.class);
    }

    @Test(expected = SerializerException.class)
    public void testInvalidListDeserialization() {
        deserializeList("", Object.class);
    }

    @Test
    public void testContentType() {
        assertNotNull(serializer.getContentType());
    }

    private String serialize(Object o) {
        OutputStream stream = new ByteArrayOutputStream();
        serializer.serialize(stream, o);
        return stream.toString();
    }

    private <T> String serialize(List<T> o, Class<T> c) {
        OutputStream stream = new ByteArrayOutputStream();
        serializer.serializeList(stream, o, c);
        return stream.toString();
    }

    private <T> T deserialize(String s, Class<T> c) {
        InputStream stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        return serializer.deserialize(stream, c);
    }

    private <T> List<T> deserializeList(String s, Class<T> c) {
        InputStream stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        return serializer.deserializeList(stream, c);
    }

}
