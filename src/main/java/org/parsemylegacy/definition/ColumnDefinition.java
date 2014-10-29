package org.parsemylegacy.definition;

import org.parsemylegacy.utils.TrimDirection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class ColumnDefinition {

    private final Field field;
    private final int from;
    private final int to;
    private final boolean trim;
    private final char trimCharacter;
    private final TrimDirection trimDirection;

    public ColumnDefinition(
            Field field, int from, int to,
            boolean trim, char trimCharacter, TrimDirection trimDirection
    ) {
        this.field = field;
        this.from = from;
        this.to = to;
        this.trim = trim;
        this.trimCharacter = trimCharacter;
        this.trimDirection = trimDirection;
    }

    public Field field() {
        return field;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public boolean trim() {
        return trim;
    }

    public char trimCharacter() {
        return trimCharacter;
    }

    public TrimDirection trimDirection() {
        return trimDirection;
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
