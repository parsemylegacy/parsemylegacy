package org.parsemylegacy.definition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class LineDefinition {

    private int length;

    private final List<ColumnDefinition> columnDefinitions = new ArrayList<>();

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        ArrayList<ColumnDefinition> copy = new ArrayList<>();
        copy.addAll(columnDefinitions);
        return copy;
    }

    public LineDefinition add(ColumnDefinition columnDefinition) {
        this.columnDefinitions.add(columnDefinition);
        return this;
    }

    public LineDefinition addAll(List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions.addAll(columnDefinitions);
        return this;
    }

    public static LineDefinition fromClass(Class<?> clazz) {
        Line line = clazz.getAnnotation(Line.class);
        if (line != null) {
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
            List<ColumnDefinition> sortedColumnDefinitions = unsortedColumnDefinitions
                    .stream()
                    .sorted((c1, c2) -> Integer.compare(c1.from(), c2.from()))
                    .collect(Collectors.toList());
            lineDefinition.addAll(sortedColumnDefinitions);

            // Set line length
            int maxColumnTo = sortedColumnDefinitions.get(sortedColumnDefinitions.size() - 1).to();
            lineDefinition.setLength(max(line.length(), maxColumnTo));

            return lineDefinition;

        }
        throw new IllegalArgumentException("Class " + clazz + " is not annotated with " + Line.class);
    }

}
