package org.parsemylegacy.definition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LineDefinition {

    private List<ColumnDefinition> columnDefinitions = new ArrayList<ColumnDefinition>();

    public LineDefinition add(ColumnDefinition columnDefinition) {
        columnDefinitions.add(columnDefinition);
        return this;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        ArrayList<ColumnDefinition> copy = new ArrayList<ColumnDefinition>();
        copy.addAll(columnDefinitions);
        return copy;
    }

    public static LineDefinition fromClass(Class<?> clazz) {
        if (clazz.getAnnotation(Line.class) != null) {
            try {
                LineDefinition lineDefinition = new LineDefinition();

                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Column column = field.getAnnotation(Column.class);
                    if (column != null) {
                        String fieldName = field.getName();
                        String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                        Method setter = clazz.getDeclaredMethod(setterName, field.getType());
                        lineDefinition.add(
                                new ColumnDefinition(
                                        setter,
                                        column.from(),
                                        column.to()
                                )
                        );
                    }
                }
                return lineDefinition;
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Class " + clazz + " is not a valid bean (cannot find setter)", e);
            }

        }
        throw new IllegalArgumentException("Class " + clazz + " is not annotated with " + Line.class);
    }

}
