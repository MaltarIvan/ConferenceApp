package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Presentations;
import hr.fer.ruazosa.confapp.entity.Presenters;

import java.util.List;

/**
 * Created by Patrik on 16-Jun-17.
 */
public interface PresentersDAO {

    /**
     * Retrievs the presenter with a given id from database
     *
     * @param id of a presenter we are looking for
     * @return presenter that has the given id or null if there is no presenter with the given id
     */
    public Presenters getPresenterById(long id);

    /**
     * Retrievs list of presenters on a conference.
     *
     * @param conferenceId id of a conference from which we want to get the list of presenters
     * @return list of presenters on a conference
     */
    public List<Presenters> getPresentersOnConference(long conferenceId);

    /**
     * Retrievs list of presenters on a presentation.
     *
     * @param presentationId id of a presentation from which we want to get the list of presenters
     * @return list of presenters on a presentation
     */
    public List<Presentations> getPresentersPresentations(long presentationId);

}
