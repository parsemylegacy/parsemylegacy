package org.parsemylegacy.definition;

import org.junit.Test;
import org.parsemylegacy.examples.Person;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.parsemylegacy.definition.LineDefinition.fromClass;

public class LineDefinitionTest {

    @Test
    public void should_create_line_definition() throws Exception {
        LineDefinition lineDefinition = fromClass(Person.class);
        assertThat(lineDefinition).isNotNull();

        Method personFirstNameSetter = Person.class.getDeclaredMethod("setFirstname", String.class);
        Method personLastNameSetter = Person.class.getDeclaredMethod("setLastname", String.class);
        List<ColumnDefinition> columnDefinitions = lineDefinition.getColumnDefinitions();
        assertThat(columnDefinitions)
                .hasSize(2)
                .containsExactly(
                        new ColumnDefinition(personFirstNameSetter, 1, 30),
                        new ColumnDefinition(personLastNameSetter, 31, 60)
                );
    }

    @Test
    public void should_fail_to_create_line_definition_when_not_annotated() throws Exception {
        try {
            fromClass(Object.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Class class java.lang.Object is not annotated with interface org.parsemylegacy.definition.Line");
        }
    }
}
