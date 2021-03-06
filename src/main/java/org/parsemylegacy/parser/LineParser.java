package org.parsemylegacy.parser;

import org.parsemylegacy.definition.ColumnDefinition;
import org.parsemylegacy.definition.LineDefinition;

import java.lang.reflect.Field;
import java.util.List;

import static java.lang.Math.min;
import static org.parsemylegacy.definition.LineDefinitions.forClass;
import static org.parsemylegacy.utils.Strings.trim;

public class LineParser<T> {

    private final Class<T> clazz;

    public LineParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T parse(String line) {
        LineDefinition lineDefinition = forClass(clazz);

        try {
            T instance = clazz.newInstance();

            List<ColumnDefinition> columnDefinitions = lineDefinition.getColumnDefinitions();
            for (ColumnDefinition columnDefinition : columnDefinitions) {
                Field field = columnDefinition.field();
                String columnText = line.substring(
                        columnDefinition.from() - 1,
                        min(columnDefinition.to(), line.length())
                );
                String trimmedColumnText = columnDefinition.trim()
                        ? trim(columnText, columnDefinition.trimCharacter(), columnDefinition.trimDirection())
                        : columnText;
                field.set(instance, trimmedColumnText);
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ParseException("Cannot parse line", e);
        }
    }

}
