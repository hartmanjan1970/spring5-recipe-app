package guru.springframework.recipe.controllers;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jan Hartman
 */
@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping(value = "/recipe/index/{id}")
	public String getRecipeIndex(@PathVariable Long id,
								 Model model) {

		final Recipe recipe = recipeService.findById(id)
				.orElseThrow(() -> new RuntimeException("The requested recipe is not found for id: " + id));
		model.addAttribute("recipe", recipe);

		return "recipe/index";
	}
}
