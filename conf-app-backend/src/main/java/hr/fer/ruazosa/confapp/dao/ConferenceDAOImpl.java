package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Conference;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dejannovak on 05/06/17.
 */
@Repository
@Transactional
public class ConferenceDAOImpl implements ConferenceDAO {

    /**
     * Injects the session factory when needed.
     * <p>
     * Sesssion factory is defined ni the hibernate configuration section of the dispatcher servlet
     */
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Conference> getConferences() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Conference> theQuery = currentSession.createQuery("from Conference", Conference.class);
        List<Conference> conferences = theQuery.getResultList();
        if (conferences == null) {
            return new ArrayList<Conference>();
        }
        return conferences;
    }

    @Override
    public Conference getConferenceById(long conferenceId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Conference> theQuery = currentSession.createQuery("from Conference where id = " + conferenceId, Conference.class);
        Conference conference = theQuery.getSingleResult();
        if (conference == null) {
            return new Conference();
        }
        return conference;
    }


}
