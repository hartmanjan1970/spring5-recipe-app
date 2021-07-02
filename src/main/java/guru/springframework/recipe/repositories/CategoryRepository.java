package guru.springframework.recipe.repositories;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jan Hartman
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
