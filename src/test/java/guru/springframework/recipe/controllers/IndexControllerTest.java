package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
		verify(indexController, times(1)).getIndexPage(model);
	}

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		indexController = new IndexController(recipeService);
	}
}