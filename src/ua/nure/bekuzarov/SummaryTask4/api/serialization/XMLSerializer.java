package ua.nure.bekuzarov.SummaryTask4.api.serialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Serializer;
import ua.nure.bekuzarov.SummaryTask4.exception.SerializerException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality for serialization to XML
 * and deserialization from XML
 */
@Serializer("xml")
public class XMLSerializer implements StreamSerializer {

    private static final String CONTENT_TYPE = "text/xml";
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLSerializer.class);

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public void serialize(OutputStream stream, Object o) {
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(o, stream);
        } catch (Exception e) {
            LOGGER.warn("Cannot serialize object.", e);
            throw new SerializerException("Cannot serialize object", e);
        }
    }

    @Override
    public <E> void serializeList(OutputStream stream, List<E> o, Class<E> c) {
        try {
            Wrapper<?> wrapper = new Wrapper<>((List<?>) o);
            JAXBContext context = JAXBContext.newInstance(Wrapper.class, c);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(wrapper, stream);
        } catch (Exception e) {
            LOGGER.warn("Cannot serialize list of objects.", e);
            throw new SerializerException("Cannot deserialize list of objects", e);
        }
    }

    @Override
    public <E> E deserialize(InputStream stream, Class<E> c) {
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller un = context.createUnmarshaller();
            return c.cast(un.unmarshal(stream));
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize object.", e);
            throw new SerializerException("Cannot deserialize object", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> List<E> deserializeList(InputStream stream, Class<E> c) {
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class, c);
            Unmarshaller un = context.createUnmarshaller();
            return ((Wrapper<E>) un.unmarshal(stream)).getItems();
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize list of object.", e);
            throw new SerializerException("Cannot deserialize list of objects", e);
        }
    }

    @XmlRootElement(name = "list")
    @SuppressWarnings("unused")
    private static final class Wrapper<T> {

        private List<T> items;

        /**
         * Creates an empty .
         */
        public Wrapper() {
            this.items = new ArrayList<>();
        }

        /**
         * Creates a wrapper for specified items.
         *
         * @param items list of items
         */
        public Wrapper(List<T> items) {
            this.items = items;
        }

        /**
         * Gets wrapped items.
         *
         * @return wrapped items
         */
        @XmlElement(name = "item")
        public List<T> getItems() {
            return items;
        }

    }

}
