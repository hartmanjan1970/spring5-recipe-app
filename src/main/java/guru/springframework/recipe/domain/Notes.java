package guru.springframework.recipe.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * @author Jan Hartman
 */
@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Recipe recipe;

	@Lob
	private String recipeNotes;

	public Notes() {
	}

	public Notes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof Notes)) return false;
		final Notes other = (Notes) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$id = this.getId();
		final Object other$id = other.getId();
		if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
		final Object this$recipe = this.getRecipe();
		final Object other$recipe = other.getRecipe();
		if (this$recipe == null ? other$recipe != null : !this$recipe.equals(other$recipe)) return false;
		final Object this$recipeNotes = this.getRecipeNotes();
		final Object other$recipeNotes = other.getRecipeNotes();
		if (this$recipeNotes == null ? other$recipeNotes != null : !this$recipeNotes.equals(other$recipeNotes))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getRecipeNotes() {
		return recipeNotes;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $id = this.getId();
		result = result * PRIME + ($id == null ? 43 : $id.hashCode());
		final Object $recipe = this.getRecipe();
		result = result * PRIME + ($recipe == null ? 43 : $recipe.hashCode());
		final Object $recipeNotes = this.getRecipeNotes();
		result = result * PRIME + ($recipeNotes == null ? 43 : $recipeNotes.hashCode());
		return result;
	}

	public void setRecipeNotes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}

	public String toString() {
		return "Notes(id=" + this.getId() + ", recipe=" + this.getRecipe() + ", recipeNotes=" + this.getRecipeNotes() + ")";
	}

	protected boolean canEqual(final Object other) {
		return other instanceof Notes;
	}
}
