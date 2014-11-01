package org.parsemylegacy.definition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineDefinition {

    private final List<ColumnDefinition> columnDefinitions = new ArrayList<>();

    public LineDefinition add(ColumnDefinition columnDefinition) {
        this.columnDefinitions.add(columnDefinition);
        return this;
    }

    public LineDefinition addAll(List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions.addAll(columnDefinitions);
        return this;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        ArrayList<ColumnDefinition> copy = new ArrayList<>();
        copy.addAll(columnDefinitions);
        return copy;
    }

    public static LineDefinition fromClass(Class<?> clazz) {
        if (clazz.getAnnotation(Line.class) != null) {
            LineDefinition lineDefinition = new LineDefinition();

            List<ColumnDefinition> unsortedColumnDefinitions = new ArrayList<>();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    field.setAccessible(true);
                    unsortedColumnDefinitions.add(
                            new ColumnDefinition(
                                    field, column.from(), column.to(),
                                    column.trim(), column.trimCharacter(), column.trimDirection()
                            )
                    );
                }
            }
            // Sort column definitions
            lineDefinition.addAll(
                    unsortedColumnDefinitions
                            .stream()
                            .sorted(
                                    (c1, c2) -> Integer.compare(c1.from(), c2.from())
                            )
                            .collect(Collectors.toList())
            );
            return lineDefinition;

        }
        throw new IllegalArgumentException("Class " + clazz + " is not annotated with " + Line.class);
    }

}
