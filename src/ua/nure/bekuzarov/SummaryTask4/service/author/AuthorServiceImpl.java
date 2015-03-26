package ua.nure.bekuzarov.SummaryTask4.service.author;

import ua.nure.bekuzarov.SummaryTask4.annotation.Autowired;
import ua.nure.bekuzarov.SummaryTask4.annotation.Service;
import ua.nure.bekuzarov.SummaryTask4.db.repository.AuthorRepository;
import ua.nure.bekuzarov.SummaryTask4.entity.Author;

import java.util.List;

/**
 * Provides implementation of {@link AuthorService} interface.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public Author add(Author author) {
        return repository.add(author);
    }

    @Override
    public Author getById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Author> getAll(List<Integer> ids) {
        return repository.getAll(ids);
    }

    @Override
    public void update(Author author) {
        repository.update(author);
    }

    @Override
    public void delete(Author author) {
        repository.delete(author.getId());
    }

    @Override
    public int countAll() {
        return repository.countAll();
    }


}
