package tacos.model;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@UserDefinedType("tacos")
public class TacoUDT {

    private String name;
    private List<IngredientUDT> ingredients = new ArrayList<>();

    public TacoUDT() {

    }

    public TacoUDT(String name, List<IngredientUDT> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<IngredientUDT> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<IngredientUDT> getIngredients() {
        return ingredients;
    }
}
