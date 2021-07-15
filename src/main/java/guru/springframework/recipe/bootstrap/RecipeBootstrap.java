package guru.springframework.recipe.bootstrap;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Difficulty;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Hartman
 */
@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;

	private final RecipeRepository recipeRepository;

	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(CategoryRepository categoryRepository,
						   RecipeRepository recipeRepository,
						   UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	/**
	 * Handle an application {@link ContextRefreshedEvent} event.
	 *
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Loaded bootstrap data. {}", getRecipes());
	}

	private List<Recipe> getRecipes() {
		final List<Recipe> recipes = new ArrayList<>(2);

		// Units Of Measure
		final UnitOfMeasure uomEach = unitOfMeasureRepository.findByDescription("Each")
				.orElseThrow(() -> new RuntimeException("Expected UOM not found."));

		final UnitOfMeasure uomTablespoon = unitOfMeasureRepository.findByDescription("Tablespoon")
				.orElseThrow(() -> new RuntimeException("Expected UOM not found."));

		final UnitOfMeasure uomTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon")
				.orElseThrow(() -> new RuntimeException("Expected UOM not found."));

		final UnitOfMeasure uomDash = unitOfMeasureRepository.findByDescription("Dash")
				.orElseThrow(() -> new RuntimeException("Expected UOM not found."));

		final UnitOfMeasure uomPint = unitOfMeasureRepository.findByDescription("Pint")
				.orElseThrow(() -> new RuntimeException("Expected UOM not found."));

		final UnitOfMeasure uomCup = unitOfMeasureRepository.findByDescription("Cup")
				.orElseThrow(() -> new RuntimeException("Expected UOM not found."));

		// Categories
		final Category categoryAmerican = categoryRepository.findByCategoryName("American")
				.orElseThrow(() -> new RuntimeException("Expected Category not found."));

		final Category categoryMexican = categoryRepository.findByCategoryName("Mexican")
				.orElseThrow(() -> new RuntimeException("Expected Category not found."));

		// recipe Guacamole
		final Recipe guacamoleRecipe = new Recipe();
		guacamoleRecipe.setDescription("Perfect Guacamole");
		guacamoleRecipe.setPrepTime(10);
		guacamoleRecipe.setCookTime(0);
		guacamoleRecipe.setDifficulty(Difficulty.EASY);
		guacamoleRecipe.setDirections(" prep directions.");

		final Notes notes = new Notes();
		notes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
				"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
				"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
				"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
				"\n" +
				"\n" +
				"Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
		guacamoleRecipe.setNotes(notes);

		guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), uomEach));
		guacamoleRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), uomTablespoon));
		guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), uomTablespoon));
		guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), uomTablespoon));
		guacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), uomEach));
		guacamoleRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), uomTablespoon));
		guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), uomDash));
		guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), uomEach));

		guacamoleRecipe.getCategories().add(categoryAmerican);
		guacamoleRecipe.getCategories().add(categoryMexican);

		guacamoleRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacamoleRecipe.setServings(4);
		guacamoleRecipe.setSource("Simply Recipes");
		recipes.add(guacamoleRecipe);

//Yummy Tacos
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);

		tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
				"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
				"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
				"\n" +
				"\n" +
				"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
				"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
				"Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
				"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
				"\n" +
				"\n" +
				"Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
				"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
				"Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
				"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
				"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
				"\n" +
				"\n" +
				"Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

		tacosRecipe.setNotes(tacoNotes);

		tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), uomTablespoon));
		tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), uomTeaspoon));
		tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), uomTeaspoon));
		tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), uomTeaspoon));
		tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), uomTeaspoon));
		tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), uomEach));
		tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), uomTablespoon));
		tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), uomTablespoon));
		tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), uomTablespoon));
		tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), uomTablespoon));
		tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), uomEach));
		tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), uomCup));
		tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), uomEach));
		tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), uomEach));
		tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), uomPint));
		tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), uomEach));
		tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), uomEach));
		tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), uomCup));
		tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), uomEach));

		tacosRecipe.getCategories().add(categoryAmerican);
		tacosRecipe.getCategories().add(categoryMexican);

		tacosRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		tacosRecipe.setServings(4);
		tacosRecipe.setSource("Simply Recipes");

		recipes.add(tacosRecipe);

		return recipes;
	}

}
