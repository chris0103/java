package tacos.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public class Taco {

    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;

    private Date createdAt = new Date();

    public Taco() {

    }

    public Taco(Long id, String name, List<IngredientRef> ingredients, Date createdAt) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", createdAt=" + createdAt +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<IngredientRef> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<IngredientRef> getIngredients() {
        return ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
