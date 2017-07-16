package hr.fer.ruazosa.confapp.dao;

import hr.fer.ruazosa.confapp.entity.Presenters;
import hr.fer.ruazosa.confapp.entity.Sponsors;
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

import hr.fer.ruazosa.confapp.entity.Presenters;
import hr.fer.ruazosa.confapp.entity.Sponsors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 06/20/17.
 */

@Repository
@Transactional
public class SponsorsDAOImpl implements SponsorsDAO {

    /**
     * Injects the session factory when needed.
     *
     */
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Sponsors> getSponsors(long conferenceId) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.query.Query<Sponsors> theQuery = session.createQuery(
                "from Sponsors as spon where spon.id in " +
                        "(select soc.sponsorId from SponsorsOnConferences as soc " +
                        "where soc.conferenceId=" + conferenceId +")", Sponsors.class);
        List<Sponsors> sponsors = theQuery.getResultList();
        if (sponsors == null) {
            return new ArrayList<Sponsors>();
        }
        return sponsors;
    }


}
