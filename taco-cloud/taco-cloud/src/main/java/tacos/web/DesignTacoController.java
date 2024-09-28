package tacos.web;
import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.data.JdbcIngredientRepository;
import tacos.pojo.Ingredient;
import tacos.pojo.Taco;
import tacos.pojo.Ingredient.Type;
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	@Autowired
	private final JdbcIngredientRepository ingredientRepo;
	@Autowired 
	 public DesignTacoController(
	 JdbcIngredientRepository ingredientRepo) {
	 this.ingredientRepo = ingredientRepo;
	 }
	 @ModelAttribute
	 public void addIngredientsToModel(Model model) {
	 List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
	 Type[] types = Ingredient.Type.values();
	 for (Type type : types) {
	 model.addAttribute(type.toString().toLowerCase(),
	 filterByType(ingredients, type));
	 }
	 }


 @GetMapping
 public String showDesignForm(Model model) {
 model.addAttribute("taco", new Taco());
 return "design";
 }
 @PostMapping
 public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
 if (errors.hasErrors()) {
 return "design";
 }
 log.info("Processing taco: " + taco);
 return "redirect:/orders/current";
 }

 private List<Ingredient> filterByType(
 List<Ingredient> ingredients, Type type) {
 return ingredients
 .stream()
 .filter(x -> x.getType().equals(type))
 .collect(Collectors.toList());
 }
 
}

