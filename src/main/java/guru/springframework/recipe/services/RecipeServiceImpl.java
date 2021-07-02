package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Jan Hartman
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Optional<Recipe> findById(Long id) {
		if (id == null) {
			return Optional.empty();
		}

		return recipeRepository.findById(id);
	}

	@Override
	public Set<Recipe> getAllRecipes() {
		Set<Recipe> recipesSet = new HashSet();

		recipeRepository.findAll()
				.forEach(recipesSet::add);

		return recipesSet;
	}
}
