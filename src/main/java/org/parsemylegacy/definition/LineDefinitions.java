package org.parsemylegacy.definition;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.parsemylegacy.definition.LineDefinition.fromClass;

public class LineDefinitions {

    private static Map<Class<?>, LineDefinition> lineDefinitionCache = new ConcurrentHashMap<>();

    public static LineDefinition getForClass(Class<?> clazz) {
        return lineDefinitionCache.computeIfAbsent(clazz, (key) -> fromClass(clazz));
    }

}
