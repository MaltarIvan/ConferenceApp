package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Presentations;
import hr.fer.ruazosa.confapp.entity.Presenters;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
public class PresentersDAOImpl implements  PresentersDAO {

    /**
     * Injects the session factory when needed.
     *
     */
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Presenters getPresenterById(long presenterId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Presenters> theQuery = session.createQuery("from Presenters where id=" + presenterId, Presenters.class);
        Presenters presenter = theQuery.getSingleResult();

        if(presenter == null){
            return new Presenters();
        }

        return presenter;
    }

    @Override
    public List<Presenters> getPresentersOnConference(long conferenceId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Presenters> theQuery =
                session.createQuery("from Presenters as prez where prez.id in" +
                        "(select poc.presenterId from PresentersOnConferences as poc " +
                        "where poc.conferenceId = " + conferenceId + ")", Presenters.class);
        List<Presenters> presenters = theQuery.getResultList();
        if(presenters == null){
            return new ArrayList<Presenters>();
        }
        return presenters;
    }

    @Override
    public List<Presentations> getPresentersPresentations(long presenterId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Presentations> theQuery =
                session.createQuery("from Presentations as prez where prez.id in" +
                        " (select pop.presentationId from PresentersOnPresentations as pop " +
                        "where pop.presenterId = " + presenterId +")", Presentations.class);
        List<Presentations> presentations = theQuery.getResultList();
        if(presentations == null){
            return new ArrayList<Presentations>();
        }
        return presentations;
    }


}
