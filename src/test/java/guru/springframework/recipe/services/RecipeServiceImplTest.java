package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jan Hartman
 */
class RecipeServiceImplTest {

	Recipe recipe1;

	@Mock
	RecipeRepository recipeRepository;

	RecipeServiceImpl recipeService;

	@Test
	void findById() {
		// given
		when(recipeRepository.findById(recipe1.getId()))
				.thenReturn(Optional.of(recipe1));

		// when
		final Optional<Recipe> recipeOptionalToVerify = recipeService.findById(recipe1.getId());

		// then
		assertThat(recipeOptionalToVerify).isNotEmpty();
		assertThat(recipeOptionalToVerify).hasValue(recipe1);
		verify(recipeRepository).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	void findByIdNotFound() {
		// given
		when(recipeRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		// when
		final Optional<Recipe> recipeOptionalToVerify = recipeService.findById(recipe1.getId());

		// then
		assertThat(recipeOptionalToVerify).isEmpty();
		verify(recipeRepository).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	void getAllRecipes() {
		// given
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(recipe1);

		when(recipeRepository.findAll())
				.thenReturn(recipesData);

		// when
		final Set<Recipe> allRecipes = recipeService.getAllRecipes();

		// then
		assertThat(allRecipes.size()).isEqualTo(recipesData.size());
		verify(recipeRepository, times(1)).findAll();
		verify(recipeRepository).findAll();
	}

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository);

		recipe1 = new Recipe();
		recipe1.setId(1L);
	}
}
