package ua.nure.bekuzarov.SummaryTask4.db.repository;

import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.db.extractor.Extractor;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.entity.Subscription;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static ua.nure.bekuzarov.SummaryTask4.db.QueryStorage.get;

/**
 * A repository for subscriptions.
 */
@Repository
public class SubscriptionRepository extends AbstractRepository<Subscription> {

    private Extractor<Subscription> extractor = new Extractor<>(Subscription.class);

    /**
     * Creates a new {@code SubscriptionRepository} object.
     *
     * @param connectionHolder connection holder
     */
    public SubscriptionRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Subscription add(Subscription subscription) {
        return add(subscription, get("subscription.insert"));
    }

    @Override
    public void update(Subscription subscription) {
        update(subscription, get("subscription.update"));
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public Subscription getById(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAll() {
        return getAll(get("subscription.select.all"), extractor);
    }

    @Override
    public List<Subscription> getAll(List<Integer> ids) {
        return getAll(ids, get("subscription.select.all.by.ids"), extractor);
    }

    /**
     * Defines whether specified subscription number exists.
     *
     * @param id subscription number
     * @return {@code true} if exists, {@code false} otherwise
     */
    public boolean exists(int id) {
        return exists(id, get("subscription.count.by.id"));
    }

    @Override
    protected void prepareForInsert(Subscription subscription, PreparedStatement ps) throws SQLException {
        ps.setInt(1, subscription.getUserId());
        ps.setDate(2, subscription.getExpirationDate());
    }

    @Override
    protected void prepareForUpdate(Subscription subscription, PreparedStatement ps) throws SQLException {
        ps.setDate(1, subscription.getExpirationDate());
        ps.setInt(2, subscription.getUserId());
    }
}
