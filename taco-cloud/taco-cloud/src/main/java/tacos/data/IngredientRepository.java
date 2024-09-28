package tacos.data;
import java.util.Optional;

import tacos.pojo.Ingredient;
public interface IngredientRepository {
 Iterable<Ingredient> findAll();
 Optional<Ingredient> findById(String id);
 Ingredient save(Ingredient ingredient);
}
