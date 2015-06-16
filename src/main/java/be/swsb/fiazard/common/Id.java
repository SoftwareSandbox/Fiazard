package be.swsb.fiazard.common;


import be.swsb.fiazard.common.exceptions.IllegalIdFiazardException;
import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.UUID;

public class Id implements Serializable {


    private UUID id;

    public static Id fromString(String id) {
        if (Strings.isNullOrEmpty(id)) {
            return null;
        }

        UUID uuid;

        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalIdFiazardException(id);
        }

        return new Id(uuid);
    }

    public static boolean isValid(String string) {
        boolean result = true;

        try {
            UUID.fromString(string);
        } catch (IllegalArgumentException e) {
            result = false;
        }

        return result;
    }

    public static Id fromUUID(UUID id) {
        return new Id(id);
    }

    public static Id random() {
        return new Id(UUID.randomUUID());
    }

    public static String asString(Id id) {
        return id == null ? null : id.toString();
    }

    private Id(UUID id) {
        this.id = id;
    }

    public UUID asUUID() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Id other = (Id) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
