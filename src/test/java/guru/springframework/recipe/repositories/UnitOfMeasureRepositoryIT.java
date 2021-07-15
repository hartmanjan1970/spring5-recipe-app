package guru.springframework.recipe.repositories;

import guru.springframework.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Jan Hartman
 */
@DataJpaTest
class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Test
	void findByDescriptionCup() {

		final String uomGiven = "Cup";
		final Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(uomGiven);

		assertTrue(unitOfMeasureOptional.isPresent());
		assertThat(unitOfMeasureOptional.get().getDescription()).isEqualTo(uomGiven);
	}

	@Test
	void findByDescriptionTeaspoon() {

		final String uomGiven = "Teaspoon";
		final Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(uomGiven);

		assertTrue(unitOfMeasureOptional.isPresent());
		assertThat(unitOfMeasureOptional.get().getDescription()).isEqualTo(uomGiven);
	}

	@BeforeEach
	void setUp() {
	}
}
