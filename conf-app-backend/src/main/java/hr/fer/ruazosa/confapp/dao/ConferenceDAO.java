package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Conference;
import hr.fer.ruazosa.confapp.entity.Presentations;
import hr.fer.ruazosa.confapp.entity.Presenters;
import hr.fer.ruazosa.confapp.entity.Sponsors;

import java.util.List;

/**
 * Created by dejannovak on 05/06/17.
 */
public interface ConferenceDAO {

    /**
     * Retrieves all the Contact entries from the database in a List format
     *
     * @return	The List of Contact objects.
     */
    public List<Conference> getConferences();

    /**
     * Retrieves conference with given ID from database
     *
     * @return Conference object
     */

    public Conference getConferenceById(long conferenceId);

}
