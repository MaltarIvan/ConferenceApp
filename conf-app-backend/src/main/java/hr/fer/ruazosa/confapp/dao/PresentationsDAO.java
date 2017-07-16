package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Presentations;
import hr.fer.ruazosa.confapp.entity.Presenters;

import java.util.List;

/**
 * Created by Patrik on 16-Jun-17.
 */
public interface PresentationsDAO {

    /**
     * Retrieves presentation with the given id.
     *
     * @param presentationId id of the presentations
     * @return presentation that has the given id
     */
    public Presentations getPresentationById(long presentationId);

    /**
     * Retrieves a list of presentations on a conference.
     *
     * @param conferenceId the conference id from which the method gets the presentations
     * @return list of presentations
     */
    public List<Presentations> getPresentationsOnConference(long conferenceId);

    /**
     * Retrieves a list of presentations which is the presenter holding on a conference.
     *
     * @param presenterId
     * @return list of presentations that is the presenter holding on the conference
     */
    public List<Presenters> getPresentersOnPresentation(long presenterId);
}
