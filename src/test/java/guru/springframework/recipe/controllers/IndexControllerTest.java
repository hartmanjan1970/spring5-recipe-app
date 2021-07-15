package guru.springframework.recipe.controllers;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
		// given
		Set<Recipe> recipes = new HashSet<>();
		final Recipe recipeA = new Recipe();
		recipeA.setId(1L);
		recipes.add(recipeA);
		final Recipe recipeB = new Recipe();
		recipeB.setId(2L);
		recipes.add(recipeB);

		when(recipeService.getAllRecipes()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		// When
		final String indexPage = indexController.getIndexPage(model);

		// Then
		assertThat(indexPage).isEqualTo("index");
		verify(recipeService, times(1)).getAllRecipes();
		verify(model, times(1))
				.addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setCapuredIndexController = argumentCaptor.getValue();
		assertThat(setCapuredIndexController.size()).isEqualTo(recipes.size());
	}

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		indexController = new IndexController(recipeService);
	}

	@Test
	void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}
}
