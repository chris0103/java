package tacos;

import java.util.List;

public class Taco {

    private String name;
    private List<Ingredient> ingredients;

    public Taco() {

    }

    public Taco(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

}
