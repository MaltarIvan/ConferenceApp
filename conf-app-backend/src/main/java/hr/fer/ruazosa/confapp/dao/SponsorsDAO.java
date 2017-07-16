package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Sponsors;

import java.util.List;

/**
 * Created by Patrik on 16-Jun-17.
 */
public interface SponsorsDAO {

    /**
     * Retrieves all the Contact entries from the database in a List format
     *
     * @return	The List of Contact objects.
     */
    public List<Sponsors> getSponsors(long id);

}
