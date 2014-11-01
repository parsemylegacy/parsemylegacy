package org.parsemylegacy.writer;

import org.parsemylegacy.definition.ColumnDefinition;
import org.parsemylegacy.definition.LineDefinition;
import org.parsemylegacy.parser.ParseException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.parsemylegacy.definition.LineDefinitions.forClass;

public class LineWriter<T> {

    private final Class<T> clazz;

    public LineWriter(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String write(T obj) {
        LineDefinition lineDefinition = forClass(clazz);

        try {
            // Init line
            int lineLength = lineDefinition.getLength();
            final char[] array = new char[lineLength];
            Arrays.fill(array, ' ');
            StringBuilder lineBuilder = new StringBuilder(0);
            lineBuilder.append(new String(array));

            // Fill line with non-null values
            List<ColumnDefinition> columnDefinitions = lineDefinition.getColumnDefinitions();
            for (ColumnDefinition columnDefinition : columnDefinitions) {
                Field field = columnDefinition.field();
                Object value = field.get(obj);
                if (value != null) {
                    lineBuilder.insert(columnDefinition.from() - 1, value.toString());
                }
            }

            return lineBuilder.toString().substring(0, lineLength);
        } catch (IllegalAccessException e) {
            throw new ParseException("Cannot write line", e);
        }
    }

}
