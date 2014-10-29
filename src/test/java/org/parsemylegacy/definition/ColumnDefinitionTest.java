package org.parsemylegacy.definition;

import org.junit.Test;
import org.parsemylegacy.examples.Person;
import org.parsemylegacy.utils.TrimDirection;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class ColumnDefinitionTest {

    @Test
    public void should_calculate_hashcode() throws Exception {
        Field personFirstNameField = Person.class.getDeclaredField("firstname");
        ColumnDefinition columnDefinition1 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        ColumnDefinition columnDefinition2 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        assertThat(columnDefinition1.hashCode()).isEqualTo(columnDefinition2.hashCode());
    }

    @Test
    public void should_compare_with_null_object() throws Exception {
        Field personFirstNameField = Person.class.getDeclaredField("firstname");
        ColumnDefinition columnDefinition1 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        assertThat(columnDefinition1.equals(null)).isFalse();
    }

    @Test
    public void should_compare_with_object_of_another_type() throws Exception {
        Field personFirstNameField = Person.class.getDeclaredField("firstname");
        ColumnDefinition columnDefinition1 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        assertThat(columnDefinition1.equals(new Object())).isFalse();
    }

    @Test
    public void should_compare_same_objects() throws Exception {
        Field personFirstNameField = Person.class.getDeclaredField("firstname");
        ColumnDefinition columnDefinition1 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        assertThat(columnDefinition1.equals(columnDefinition1)).isTrue();
    }

    @Test
    public void should_compare_equal_objects() throws Exception {
        Field personFirstNameField = Person.class.getDeclaredField("firstname");
        ColumnDefinition columnDefinition1 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        ColumnDefinition columnDefinition2 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        assertThat(columnDefinition1.equals(columnDefinition2)).isTrue();
    }

    @Test
    public void should_compare_different_objects() throws Exception {
        Field personFirstNameField = Person.class.getDeclaredField("firstname");
        ColumnDefinition columnDefinition1 = new ColumnDefinition(personFirstNameField, 1, 25, true, ' ', TrimDirection.RIGHT);
        ColumnDefinition columnDefinition2 = new ColumnDefinition(personFirstNameField, 99, 111, true, ' ', TrimDirection.RIGHT);
        assertThat(columnDefinition1.equals(columnDefinition2)).isFalse();
    }
}
