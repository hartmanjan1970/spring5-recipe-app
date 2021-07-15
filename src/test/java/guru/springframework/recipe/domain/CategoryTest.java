package guru.springframework.recipe.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jan Hartman
 */
class CategoryTest {

	private Category category;

	@BeforeEach
	public void setUp() {
		category = new Category();
	}

	@Test
	void getDescription() {
	}

	@Test
	void getId() {
		Long idValue = 666L;
		category.setId(idValue);

		assertThat(category.getId()).isEqualTo(idValue);
	}

	@Test
	void getRecipes() {
	}
}
