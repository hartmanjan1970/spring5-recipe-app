package guru.springframework.recipe.repositories;

import guru.springframework.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jan Hartman
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByCategoryName(String categoryName);
}
