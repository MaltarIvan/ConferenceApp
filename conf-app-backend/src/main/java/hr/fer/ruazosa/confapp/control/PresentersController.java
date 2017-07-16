package hr.fer.ruazosa.confapp.control;

import hr.fer.ruazosa.confapp.dao.PresentersDAO;
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
 * Created by Ivan on 6/20/2017.
 */
@RestController
public class PresentersController {

    @Autowired
    private PresentersDAO presentersDAO;

    @RequestMapping(value = "/presenters/{id}")
    @ResponseBody
    public Presenters presenterById(Model theModel, @PathVariable("id") long presenterId) {

        Presenters thePresenter = presentersDAO.getPresenterById(presenterId);
        return thePresenter;
    }

    @RequestMapping(value = "/conferences/{id}/presenters")
    @ResponseBody
    public List<Presenters> listPresenters(Model theModel, @PathVariable("id") long conferenceid) {

        List<Presenters> thePresenters = presentersDAO.getPresentersOnConference(conferenceid);
        return thePresenters;
    }

    @RequestMapping(value = "/conferences/{id}/presenters/{presenterid}")
    @ResponseBody
    public List<Presentations> listPresentersPresentation(Model theModel, @PathVariable("id") long conferenceid,
                                                       @PathVariable("presenterid") long presenterid) {

        List<Presentations> thePresentersPresentation = presentersDAO.getPresentersPresentations(presenterid);
        return thePresentersPresentation;
    }
}

