package tacos.model;


import org.springframework.data.relational.core.mapping.Table;

@Table
public class IngredientRef {

    private final String ingredient;

    public IngredientRef(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }
}
