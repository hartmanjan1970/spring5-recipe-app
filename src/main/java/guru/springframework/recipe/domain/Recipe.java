package guru.springframework.recipe.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jan Hartman
 */
@Data
@Entity
public class Recipe {

	@ManyToMany
	@JoinTable(name = "recipe_category",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	private Integer cookTime;

	private String description;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@Lob
	private String directions;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private Byte[] image;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	private Integer prepTime;

	private Integer servings;

	private String source;

	private String url;

	public Recipe addIngredient(Ingredient ingredient) {
		if (ingredient == null) {
			return this;
		}

		if (ingredients == null) {
			ingredients = new HashSet<>();
		}
		ingredients.add(ingredient);
		ingredient.setRecipe(this); // ingedient - recept is bi directionele relatie.
		return this;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;

		// de note heeft link terug naar dit recept, dus zet deze ook.
		notes.setRecipe(this);
	}

}
