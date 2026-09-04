package tacos.model;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("ingredients")
public class IngredientUDT {

    private String name;
    private Ingredient.Type type;

    public IngredientUDT(String name, Ingredient.Type type) {
        this.name = name;
        this.type = type;
    }

    private IngredientUDT() {

    }

    @Override
    public String toString() {
        return "IngredientUDT{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Ingredient.Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Ingredient.Type getType() {
        return type;
    }
}
