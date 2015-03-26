package ua.nure.bekuzarov.SummaryTask4.service.publisher;

import ua.nure.bekuzarov.SummaryTask4.annotation.Autowired;
import ua.nure.bekuzarov.SummaryTask4.annotation.Service;
import ua.nure.bekuzarov.SummaryTask4.db.repository.PublisherRepository;
import ua.nure.bekuzarov.SummaryTask4.entity.Publisher;

import java.util.List;

/**
 * Provides implementation of {@link PublisherService} interface.
 */
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository repository;

    @Override
    public Publisher add(Publisher publisher) {
        return repository.add(publisher);
    }

    @Override
    public Publisher getById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Publisher> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Publisher publisher) {
        repository.update(publisher);
    }

    @Override
    public void delete(Publisher publisher) {
        repository.delete(publisher.getId());
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }
}
