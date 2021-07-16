package guru.springframework.recipe.controllers;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jan Hartman
 */
@Slf4j
@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping(value = "/recipe/index/{id}")
	public String getRecipeIndex(@PathVariable String id,
								 Model model) {
		log.debug("Getting the Recipe index.");

		final Recipe recipe = recipeService.findById(Long.parseLong(id))
				.orElseThrow(() -> new RuntimeException("The requested recipe is not found for id: " + id));
		model.addAttribute("recipe", recipe);

		return "recipe/index";
	}
}
