package tacos.model;

import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

@Table
public class Ingredient implements Persistable<String>  {

    @Id
    private String id;

    private String name;
    private Type type;

    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
