package model;

import java.util.Objects;

public class Airport {
    private final String code;
    private final String name;

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public String getName() { return name; }

    @Override
    public String toString() { return code + " (" + name + ")"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return code.equals(airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
