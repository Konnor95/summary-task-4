package ua.nure.bekuzarov.SummaryTask4.db.repository;

import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.db.QueryStorage.get;

/**
 * A repository for translations.
 */
@Repository
public class TranslationRepository extends AbstractRepository<Translation> {

    private Extractor<Translation> extractor = new Extractor<>(Translation.class);

    /**
     * Creates a new {@code TranslationRepository} object.
     *
     * @param connectionHolder connection holder
     */
    public TranslationRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Translation add(Translation translation) {
        return add(translation, get("translation.insert"));
    }

    @Override
    public void update(Translation translation) {
        update(translation, get("translation.update"));
    }

    @Override
    public void delete(int id) {
        deleteById(id, get("translation.delete.by.id"));
    }

    @Override
    public Translation getById(int id) {
        return getById(id, get("translation.select.by.id"), extractor);
    }

    @Override
    public List<Translation> getAll() {
        return getAll(get("translation.select.all"), extractor);
    }

    @Override
    public List<Translation> getAll(List<Integer> ids) {
        return getAll(ids, get("translation.select.all.by.ids"), extractor);
    }

    @Override
    protected void prepareForInsert(Translation entity, PreparedStatement ps) throws SQLException {
        List<String> values = entity.values();
        for (int i = 0; i < values.size(); i++) {
            ps.setString(i + 1, values.get(i));
        }
    }

    @Override
    protected void prepareForUpdate(Translation translation, PreparedStatement ps) throws SQLException {
        List<String> values = translation.values();
        int i = 0;
        for (; i < values.size(); i++) {
            ps.setString(i + 1, values.get(i));
        }
        ps.setInt(i + 1, translation.getId());
    }

}
