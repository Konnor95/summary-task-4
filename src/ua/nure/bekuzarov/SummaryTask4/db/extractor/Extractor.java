package ua.nure.bekuzarov.SummaryTask4.db.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Column;
import ua.nure.bekuzarov.SummaryTask4.annotation.Extract;
import ua.nure.bekuzarov.SummaryTask4.annotation.Prefix;
import ua.nure.bekuzarov.SummaryTask4.exception.ExtractionException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracts an object from {@link ResultSet}
 *
 * @param <T> type of the object to extract
 * @see ResultSet
 * @see ua.nure.bekuzarov.SummaryTask4.annotation.Extract
 * @see ua.nure.bekuzarov.SummaryTask4.annotation.Column
 * @see ua.nure.bekuzarov.SummaryTask4.annotation.Prefix
 */
public class Extractor<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Extractor.class);
    private Class<T> objectClass;
    private List<Field> listFields = new ArrayList<>();

    /**
     * Instantiates a new extractor
     *
     * @param objectClass Parent class for objects that can be extracted by this extractor.
     */
    public Extractor(Class<T> objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * Extracts an object
     *
     * @param resultSet Result set to extract from
     * @return extracted object
     * @throws SQLException if cannot extract object
     */
    public T extract(ResultSet resultSet) throws SQLException {
        try {
            Class<T> c = objectClass;
            T instance = c.newInstance();
            Prefix prefixAnnotation = c.getAnnotation(Prefix.class);
            String prefix = null;
            if (prefixAnnotation != null) {
                prefix = prefixAnnotation.value();
            }
            for (Class<? super T> cl = c.getSuperclass(); cl != Object.class; cl = cl.getSuperclass()) {
                fillFields(cl, resultSet, instance, prefix);
            }
            fillFields(c, resultSet, instance, prefix);
            fillListFields(instance, resultSet);
            return instance;
        } catch (IllegalAccessException e) {
            LOGGER.warn("Cannot set field.", e);
            throw new ExtractionException("Cannot set field", e);
        } catch (InstantiationException e) {
            LOGGER.warn("Cannot create object.", e);
            throw new ExtractionException("Cannot create object", e);
        }
    }

    /**
     * Extracts object from {@code resultSet} with specified {@code prefix}.
     *
     * @param resultSet result set to extract from
     * @param prefix    prefix
     * @return extracted object
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private T extractOne(ResultSet resultSet, String prefix)
            throws SQLException, IllegalAccessException, InstantiationException {
        Class<T> c = objectClass;
        T instance = c.newInstance();
        for (Class<? super T> cl = c.getSuperclass(); cl != Object.class; cl = cl.getSuperclass()) {
            fillFields(cl, resultSet, instance, prefix);
        }
        fillFields(c, resultSet, instance, prefix);
        return instance;
    }

    /**
     * Fills fields of extracted object.
     *
     * @param c         class of extracted object
     * @param resultSet result set to extract from
     * @param instance  extracted object
     * @param prefix    fields prefix
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void fillFields(Class c, ResultSet resultSet, T instance, String prefix)
            throws SQLException, IllegalAccessException, InstantiationException {
        for (Field field : c.getDeclaredFields()) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            if (column == null) {
                extract(field, resultSet, instance, prefix);
                continue;
            }
            String label = column.value();
            if (label == null || label.isEmpty()) {
                label = field.getName();
            }
            if (prefix != null && !prefix.isEmpty()) {
                label = prefix + "_" + label;
            }
            Object object = resultSet.getObject(label, field.getType());
            field.set(instance, object);
        }
    }

    /**
     * Extracts inner object and sets as the field value of outer object.
     *
     * @param field     field to set
     * @param resultSet result set to extract from
     * @param instance  extracted object
     * @param prefix    field prefix
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void extract(Field field, ResultSet resultSet, Object instance, String prefix)
            throws SQLException, IllegalAccessException, InstantiationException {
        Extract extract = field.getAnnotation(Extract.class);
        if (extract != null) {

            if (field.getGenericType() instanceof ParameterizedType) {
                listFields.add(field);
                return;
            }

            Extractor<?> extractor = new Extractor<>(field.getType());
            String value = extract.value();
            if (value != null && !value.isEmpty()) {
                field.set(instance, extractor.extractOne(resultSet, value));
            } else {
                String fieldPrefix = prefix == null || prefix.isEmpty() ? "" : prefix + "_";
                field.set(instance, extractor.extractOne(resultSet, fieldPrefix + field.getName()));
            }
        }
    }

    /**
     * Fills field with a list of extracted inner objects.
     *
     * @param instance  outer extracted object
     * @param resultSet result set to extract from
     * @throws SQLException
     * @throws IllegalAccessException
     */
    private void fillListFields(Object instance, ResultSet resultSet)
            throws SQLException, IllegalAccessException {
        for (Field field : listFields) {
            Type genericFieldType = field.getGenericType();
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Class<?> listType = (Class) aType.getActualTypeArguments()[0];
            List<Object> list = new ArrayList<>();
            int prev = resultSet.getInt(1);
            for (int current = prev; prev == current; current = resultSet.getInt(1)) {
                Extractor<?> extractor = new Extractor<>(listType);
                Object o = extractor.extract(resultSet);
                list.add(o);
                if (!resultSet.next()) {
                    break;
                }
            }
            resultSet.previous();
            field.set(instance, list);
        }
        listFields = new ArrayList<>();
    }
}

