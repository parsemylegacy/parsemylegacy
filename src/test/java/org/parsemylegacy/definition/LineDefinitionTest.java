package org.parsemylegacy.definition;

import org.junit.Test;
import org.parsemylegacy.examples.Person;
import org.parsemylegacy.utils.TrimDirection;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.parsemylegacy.definition.LineDefinition.fromClass;

public class LineDefinitionTest {

    @Test
    public void should_create_line_definition() throws Exception {
        LineDefinition lineDefinition = fromClass(Person.class);
        assertThat(lineDefinition).isNotNull();

        Field personFirstNameField = Person.class.getDeclaredField("firstname");
        Field personLastNameField = Person.class.getDeclaredField("lastname");
        List<ColumnDefinition> columnDefinitions = lineDefinition.getColumnDefinitions();
        assertThat(columnDefinitions)
                .hasSize(2)
                .containsExactly(
                        new ColumnDefinition(personFirstNameField, 1, 30).withTrim(true).withTrimCharacter(' ').withTrimDirection(TrimDirection.RIGHT),
                        new ColumnDefinition(personLastNameField, 31, 60).withTrim(true).withTrimCharacter(' ').withTrimDirection(TrimDirection.RIGHT)
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
