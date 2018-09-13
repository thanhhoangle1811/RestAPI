package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookStoreController {
	@RequestMapping(value="/showAll.html", method = RequestMethod.GET)
    public String showAll(ModelMap model){
		
        return "showAll";
    }
}
