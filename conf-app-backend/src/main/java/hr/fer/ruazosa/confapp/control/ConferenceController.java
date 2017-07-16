package hr.fer.ruazosa.confapp.control;

import hr.fer.ruazosa.confapp.dao.ConferenceDAO;
import hr.fer.ruazosa.confapp.entity.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dejannovak on 05/06/17.
 */

@RestController
public class ConferenceController {


    /**
     * Injects the Contact service into controller
     */
    @Autowired
    private ConferenceDAO conferenceDAO;

    @RequestMapping(value = "/conferences")
    public  @ResponseBody List<Conference> listContacts(Model theModel){
        List<Conference> theContacts = conferenceDAO.getConferences();
        return theContacts;
    }

    @RequestMapping(value = "/conferences/{id}")
    public  @ResponseBody Conference listConference(Model theModel, @PathVariable("id") long conferenceid){
        Conference conference = conferenceDAO.getConferenceById(conferenceid);

        return conference;
    }

}
