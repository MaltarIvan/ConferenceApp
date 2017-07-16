package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Presentations;
import hr.fer.ruazosa.confapp.entity.Presenters;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrik on 16-Jun-17.
 */

@Repository
@Transactional
public class PresentationsDAOImpl implements PresentationsDAO {

    /**
     * Injects the session factory when needed.
     */
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Presentations getPresentationById(long presentationId) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.query.Query<Presentations> theQuery = session.createQuery("from Presentations where id = " + presentationId, Presentations.class);
        Presentations presentation = theQuery.getSingleResult();

        return presentation;
    }

    @Override
    public List<Presentations> getPresentationsOnConference(long conferenceId) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.query.Query<Presentations> theQuery =
                session.createQuery("from Presentations where conferenceId = " + conferenceId, Presentations.class);
        List<Presentations> presentations = theQuery.getResultList();
        if (presentations == null) {
            return new ArrayList<Presentations>();
        }
        return presentations;
    }

    @Override
    public List<Presenters> getPresentersOnPresentation(long presentationId) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.query.Query<Presenters> theQuery =
                session.createQuery("from Presenters as pres where pres.id in " +
                        "(select pop.presenterId from PresentersOnPresentations as pop " +
                        "where presentationId = " + presentationId +")", Presenters.class);
        List<Presenters> presenters = theQuery.getResultList();
        if (presenters == null) {
            return new ArrayList<Presenters>();
        }
        return presenters;
    }
}
