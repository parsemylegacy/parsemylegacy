package org.parsemylegacy.parser;

import org.junit.Test;
import org.parsemylegacy.examples.Person;
import org.parsemylegacy.examples.PersonWithId;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    @Test
    public void should_parse_people_from_text_file() {
        InputStream input = ParserTest.class.getResourceAsStream("/people.txt");
        Parser<Person> parser = new Parser<>(Person.class);
        List<Person> people = parser.parse(input);
        assertThat(people).hasSize(3);
        assertThat(people).containsExactly(
                new Person("WALTER", "WHITE"),
                new Person("JESSE", "PINKMAN"),
                new Person("SAUL", "GOODMAN")
        );
    }

    @Test
    public void should_parse_people_with_ids_from_text_file() {
        InputStream input = ParserTest.class.getClassLoader().getResourceAsStream("people_with_ids.txt");
        Parser<PersonWithId> parser = new Parser<>(PersonWithId.class);
        List<PersonWithId> people = parser.parse(input);
        assertThat(people).hasSize(4);
        assertThat(people).containsExactly(
                new PersonWithId("1", "HEISENBERG                    ", "WALTER", "WHITE"),
                new PersonWithId("2", "                              ", "JESSE", "PINKMAN"),
                new PersonWithId("99", "                              ", "SAUL", "GOODMAN"),
                new PersonWithId("999", "                              ", "MARIE", "SCHRADER")
        );
    }
}
