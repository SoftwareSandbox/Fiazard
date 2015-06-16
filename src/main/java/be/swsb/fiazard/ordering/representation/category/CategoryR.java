package be.swsb.fiazard.ordering.representation.category;

import be.swsb.fiazard.common.Id;
import be.swsb.fiazard.common.Representation;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CategoryR implements Representation {

    @NotNull
    private Id id;

    @NotNull
    @Length(max = 50)
    private String name;

    private CategoryR() {/*noop, necessary for Jackson :s, or we might need to annotate the actual constructor*/ }

    public CategoryR(Id id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return Id.asString(id);
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryR other = (CategoryR) o;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.name);
    }
}
