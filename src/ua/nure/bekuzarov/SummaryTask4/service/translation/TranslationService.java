package ua.nure.bekuzarov.SummaryTask4.service.translation;

import ua.nure.bekuzarov.SummaryTask4.entity.Translation;

/**
 * Provides functionality for working with translations.
 */
public interface TranslationService {

    /**
     * Gets translation by id.
     *
     * @param id id of the translation
     * @return found translation
     */
    Translation getById(int id);

    /**
     * Adds a new translation.
     *
     * @param translation translation to add
     * @return added translation
     */
    Translation add(Translation translation);

    /**
     * Updates an existing translation.
     *
     * @param translation translation to update.
     */
    void update(Translation translation);

}
