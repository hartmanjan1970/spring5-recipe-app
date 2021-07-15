package guru.springframework.recipe.domain;

/**
 * @author Jan Hartman
 */
public enum Difficulty {
	EASY("Easy"),
	MODERATE("Moderate"),
	KIND_OF_HARD("Kind of hard"),
	HARD("Hard");

	private final String description;

	Difficulty(String description) {
		this.description = description;
	}
}
