package guru.springframework.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author Jan Hartman
 */
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

	private String categoryName;

	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

}
