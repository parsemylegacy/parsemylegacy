package org.parsemylegacy.definition;

import org.junit.Test;
import org.parsemylegacy.examples.Person;
import org.parsemylegacy.examples.PersonWithFiller;
import org.parsemylegacy.examples.UnsortedPerson;
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

        Field personFirstnameField = Person.class.getDeclaredField("firstname");
        Field personLastnameField = Person.class.getDeclaredField("lastname");

        List<ColumnDefinition> columnDefinitions = lineDefinition.getColumnDefinitions();
        assertThat(columnDefinitions)
                .hasSize(2)
                .containsExactly(
                        new ColumnDefinition(personFirstnameField, 1, 30, true, ' ', TrimDirection.RIGHT),
                        new ColumnDefinition(personLastnameField, 31, 60, true, ' ', TrimDirection.RIGHT)
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

    @Test
    public void should_create_line_definition_and_sort_column_definitions() throws Exception {
        LineDefinition lineDefinition = fromClass(UnsortedPerson.class);
        assertThat(lineDefinition).isNotNull();

        Field personIdField = UnsortedPerson.class.getDeclaredField("id");
        Field personFirstnameField = UnsortedPerson.class.getDeclaredField("firstname");
        Field personLastnameField = UnsortedPerson.class.getDeclaredField("lastname");
        Field personNicknameField = UnsortedPerson.class.getDeclaredField("nickname");

        List<ColumnDefinition> columnDefinitions = lineDefinition.getColumnDefinitions();
        assertThat(columnDefinitions)
                .hasSize(4)
                .containsExactly(
                        new ColumnDefinition(personIdField, 1, 3, true, ' ', TrimDirection.RIGHT),
                        new ColumnDefinition(personNicknameField, 4, 33, true, ' ', TrimDirection.RIGHT),
                        new ColumnDefinition(personFirstnameField, 34, 63, true, ' ', TrimDirection.RIGHT),
                        new ColumnDefinition(personLastnameField, 64, 93, true, ' ', TrimDirection.RIGHT)
                );
    }

    @Test
    public void should_create_line_definition_with_length() {
        LineDefinition lineDefinition = fromClass(PersonWithFiller.class);
        assertThat(lineDefinition).isNotNull();

        assertThat(lineDefinition.getLength()).isEqualTo(100);
    }
}
