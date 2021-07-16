package guru.springframework.recipe.controllers;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Jan Hartman
 */
@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	RecipeController recipeController;

	@Mock
	RecipeService recipeService;

	private Recipe recipe;

	@Test
	void getRecipeIndex() throws Exception {

		when(recipeService.findById(anyLong()))
				.thenReturn(Optional.of(recipe));

		mockMvc.perform(MockMvcRequestBuilders.get("/recipe/index/{id}", recipe.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("recipe/index"))
				.andExpect(MockMvcResultMatchers.model().attribute("recipe",
						Matchers.is(recipe)));
	}

	@BeforeEach
	void setUp() {
		recipe = new Recipe();
		recipe.setId(1L);

		mockMvc = MockMvcBuilders
				.standaloneSetup(recipeController)
				.build();
	}
}
