package guru.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jan Hartman
 */
@Controller
public class IndexController {

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage() {
		return "index";
	}
}
