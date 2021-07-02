package guru.springframework.recipe.controllers;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Jan Hartman
 */
@Controller
public class IndexController {

	private final CategoryRepository categoryRepository;

	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage() {

		final Optional<Category> category = categoryRepository.findByCategoryName("American");
		final Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Pinch");

		System.out.println("Category Id is: " + category.get().getId());
		System.out.println("Unit of measure Id is:" + unitOfMeasure.get().getId());

		return "index";
	}
}
