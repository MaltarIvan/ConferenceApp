package hr.fer.ruazosa.confapp.control;

import hr.fer.ruazosa.confapp.dao.PresentationsDAO;
import hr.fer.ruazosa.confapp.entity.Presentations;
import hr.fer.ruazosa.confapp.entity.Presenters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Patrik on 20-Jun-17.
 */
@RestController
public class PresentationsController {
    /**
     * Injects the Contact service into controller
     */
    @Autowired
    private PresentationsDAO presentationsDAO;


    @RequestMapping(value = "/presentations/{id}")
    @ResponseBody
    public Presentations presentationById(Model theModel, @PathVariable("id") long presentationId) {

        Presentations thePresentation = presentationsDAO.getPresentationById(presentationId);
        return thePresentation;
    }

    @RequestMapping(value = "/conferences/{id}/presentations")
    @ResponseBody
    public List<Presentations> listPresentationsOnConference(Model theModel, @PathVariable("id") long conferenceId) {

        List<Presentations> thePresentations = presentationsDAO.getPresentationsOnConference(conferenceId);
        return thePresentations;
    }

    @RequestMapping(value = "/conferences/{conferenceId}/presentations/{presentationId}")
    @ResponseBody
    public List<Presenters> listPresentations(Model theModel, @PathVariable("conferenceId") long conferenceId,
                                                 @PathVariable("presentationId") long presentationId) {

        List<Presenters> thePresenters = presentationsDAO.getPresentersOnPresentation(presentationId);
        return thePresenters;
    }
}
