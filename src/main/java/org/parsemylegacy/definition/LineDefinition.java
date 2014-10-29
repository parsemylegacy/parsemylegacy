package org.parsemylegacy.definition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LineDefinition {

    private List<ColumnDefinition> columnDefinitions = new ArrayList<>();

    public LineDefinition add(ColumnDefinition columnDefinition) {
        columnDefinitions.add(columnDefinition);
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

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    field.setAccessible(true);
                    lineDefinition.add(
                            new ColumnDefinition(
                                    field, column.from(), column.to(),
                                    column.trim(), column.trimCharacter(), column.trimDirection()
                            )
                    );
                }
            }
            return lineDefinition;

        }
        throw new IllegalArgumentException("Class " + clazz + " is not annotated with " + Line.class);
    }

}
