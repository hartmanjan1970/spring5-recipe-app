package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Jan Hartman
 */
class IndexControllerTest {

	IndexController indexController;

	@Mock
	Model model;

	@Mock
	RecipeServiceImpl recipeService;

	@Test
	void getIndexPage() {

		// When
		final String indexPage = indexController.getIndexPage(model);

		// Then
		assertThat(indexPage).isEqualTo("index");
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
	}

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		indexController = new IndexController(recipeService);
	}
}
