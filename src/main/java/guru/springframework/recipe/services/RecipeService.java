package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;

import java.util.Optional;
import java.util.Set;

/**
 * @author Jan Hartman
 */
public interface RecipeService {

	Optional<Recipe> findById(Long id);

	Set<Recipe> getAllRecipes();
}
