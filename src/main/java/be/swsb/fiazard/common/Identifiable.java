package be.swsb.fiazard.common;

import java.io.Serializable;
import java.util.UUID;

// TODO: butskri: check if this class is a good idea?
public class Identifiable implements Serializable {

    private String id;

    public static Identifiable randomId() {
        return new Identifiable(UUID.randomUUID().toString());
    }

    public Identifiable() {
    }

    public Identifiable(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // TODO butskri: can't we use apache commons HashCodeBuilder?
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    // TODO butskri: can't we use apache commons EqualsBuilder?
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Identifiable other = (Identifiable) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
