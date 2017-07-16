package hr.fer.ruazosa.confapp.control;

import hr.fer.ruazosa.confapp.dao.SponsorsDAO;
import hr.fer.ruazosa.confapp.entity.Sponsors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by Ivan on 6/20/2017.
 */
@RestController
public class SponsorsController {

    /**
     * Injects the Contact service into controller
     */
    @Autowired
    private SponsorsDAO sponsorsDAO;

    @RequestMapping(value = "/conferences/{id}/sponsors")
    @ResponseBody public List<Sponsors> listSponsors(Model theModel, @PathVariable("id") long conferenceId){

        List<Sponsors> theSponsors = sponsorsDAO.getSponsors(conferenceId);
        return theSponsors;
    }

}
