package org.parsemylegacy.definition;

import org.parsemylegacy.utils.TrimDirection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class ColumnDefinition {

    private Field field;
    private Integer from;
    private Integer to;
    private Boolean trim;
    private Character trimCharacter;
    private TrimDirection trimDirection;

    public ColumnDefinition(Field field, Integer from, Integer to) {
        this.field = field;
        this.from = from;
        this.to = to;
    }

    public Field field() {
        return field;
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
                new Object[]{field, from, to, trim, trimCharacter, trimDirection},
                new Object[]{cd.field, cd.from, cd.to, cd.trim, cd.trimCharacter, cd.trimDirection}
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(field.getName(), from, to, trim, trimCharacter, trimDirection);
    }
}
