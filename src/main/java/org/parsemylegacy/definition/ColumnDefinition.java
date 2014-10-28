package org.parsemylegacy.definition;

import org.parsemylegacy.utils.TrimDirection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class ColumnDefinition {

    private Method setter;
    private Integer from;
    private Integer to;
    private Boolean trim;
    private Character trimCharacter;
    private TrimDirection trimDirection;

    public ColumnDefinition(Method setter, Integer from, Integer to) {
        this.setter = setter;
        this.from = from;
        this.to = to;
    }

    public Method setter() {
        return setter;
    }

    public Integer from() {
        return from;
    }

    public Integer to() {
        return to;
    }

    public Boolean trim() {
        return trim;
    }

    public Character trimCharacter() {
        return trimCharacter;
    }

    public TrimDirection trimDirection() {
        return trimDirection;
    }

    public ColumnDefinition withTrim(Boolean trim) {
        this.trim = trim;
        return this;
    }

    public ColumnDefinition withTrimCharacter(Character trimCharacter) {
        this.trimCharacter = trimCharacter;
        return this;
    }

    public ColumnDefinition withTrimDirection(TrimDirection trimDirection) {
        this.trimDirection = trimDirection;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ColumnDefinition)) {
            return false;
        }
        ColumnDefinition cd = (ColumnDefinition) obj;
        return Arrays.equals(
                new Object[]{setter, from, to, trim, trimCharacter, trimDirection},
                new Object[]{cd.setter, cd.from, cd.to, cd.trim, cd.trimCharacter, cd.trimDirection}
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(setter.getName(), from, to, trim, trimCharacter, trimDirection);
    }
}
