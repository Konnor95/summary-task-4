package ua.nure.bekuzarov.SummaryTask4.service.translation;

import ua.nure.bekuzarov.SummaryTask4.annotation.Autowired;
import ua.nure.bekuzarov.SummaryTask4.annotation.Service;
import ua.nure.bekuzarov.SummaryTask4.db.repository.TranslationRepository;
import ua.nure.bekuzarov.SummaryTask4.entity.Translation;

/**
 * Provides implementation of {@link TranslationService} interface.
 */
@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private TranslationRepository repository;

    @Override
    public Translation getById(int id) {
        return repository.getById(id);
    }

    @Override
    public Translation add(Translation translation) {
        if (translation == null) {
            return null;
        }
        return repository.add(translation);
    }

    @Override
    public void update(Translation translation) {
        repository.update(translation);
    }
}
