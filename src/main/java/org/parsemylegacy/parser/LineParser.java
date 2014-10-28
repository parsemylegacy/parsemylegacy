package org.parsemylegacy.parser;

import org.parsemylegacy.definition.ColumnDefinition;
import org.parsemylegacy.definition.LineDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static java.lang.Math.min;
import static org.parsemylegacy.definition.LineDefinitions.getForClass;

public class LineParser {


    public static <T> T parse(Class<T> clazz, String line) {
        LineDefinition lineDefinition = getForClass(clazz);

        try {
            T instance = clazz.newInstance();

            List<ColumnDefinition> columnDefinitions = lineDefinition.getColumnDefinitions();
            for (ColumnDefinition columnDefinition : columnDefinitions) {
                Method setter = columnDefinition.getSetter();
                setter.invoke(
                        instance,
                        line.substring(
                                columnDefinition.getFrom() - 1,
                                min(columnDefinition.getTo(), line.length())
                        ).trim()
                );
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ParseException("Cannot parse line", e);
        }
    }

}
