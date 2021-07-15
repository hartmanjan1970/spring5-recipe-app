package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jan Hartman
 */
class RecipeServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;

	RecipeServiceImpl recipeService;

	@Test
	void getAllRecipes() {
		// given
		Recipe recipe = new Recipe();
		Set recipesData = new HashSet();
		recipesData.add(recipe);

		when(recipeRepository.findAll())
				.thenReturn(recipesData);

		// when
		final Set<Recipe> allRecipes = recipeService.getAllRecipes();

		// then
		assertThat(allRecipes.size()).isEqualTo(recipesData.size());
		verify(recipeRepository, times(1)).findAll();
	}

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository);
	}
}
