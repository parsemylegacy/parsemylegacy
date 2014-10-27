package org.parsemylegacy.definition;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class ColumnDefinition {

    private Method setter;
    private Integer from;
    private Integer to;

    public ColumnDefinition(Method setter, Integer from, Integer to) {
        this.setter = setter;
        this.from = from;
        this.to = to;
    }

    public Method getSetter() {
        return setter;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ColumnDefinition)) {
            return false;
        }
        ColumnDefinition columnDefinition = (ColumnDefinition) obj;
        return Arrays.equals(
                new Object[]{setter, from, to},
                new Object[]{columnDefinition.setter, columnDefinition.from, columnDefinition.to}
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(setter.getName(), from, to);
    }
}
