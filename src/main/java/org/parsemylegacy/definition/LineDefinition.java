package org.parsemylegacy.definition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;

public class LineDefinition {

    private int length;

    private final List<ColumnDefinition> columnDefinitions = new ArrayList<>();

    public int getLength() {
        return length;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return new ArrayList<>(columnDefinitions);
    }

    public static LineDefinition fromClass(Class<?> clazz) {
        Line line = clazz.getAnnotation(Line.class);
        if (line != null) {
            LineDefinition lineDefinition = new LineDefinition();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    field.setAccessible(true);
                    lineDefinition.columnDefinitions.add(
                            new ColumnDefinition(
                                    field, column.from(), column.to(),
                                    column.trim(), column.trimCharacter(), column.trimDirection()
                            )
                    );
                }
            }

            // Sort column definitions and set line length
            Collections.sort(lineDefinition.columnDefinitions, (c1, c2) -> Integer.compare(c1.from(), c2.from()));
            int maxColumnTo = lineDefinition.columnDefinitions.get(lineDefinition.columnDefinitions.size() - 1).to();
            lineDefinition.length = max(line.length(), maxColumnTo);

            return lineDefinition;
        }
        throw new IllegalArgumentException("Class " + clazz + " is not annotated with " + Line.class);
    }

}
