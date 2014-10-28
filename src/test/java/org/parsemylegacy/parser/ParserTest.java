package org.parsemylegacy.parser;

import org.junit.Test;
import org.parsemylegacy.examples.Person;
import org.parsemylegacy.examples.PersonWithId;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.parsemylegacy.parser.Parser.parser;

public class ParserTest {

    @Test
    public void should_create_parser_instance() {
        assertThat(parser()).isNotNull();
    }

    @Test
    public void should_throw_illegalargumentexception_if_no_input_provided() {
        try {
            parser().parse(Person.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("No input provided! Use Parser#from(InputStream input)");
        }
    }


    @Test
    public void should_parse_people_from_text_file() {
        InputStream input = ParserTest.class.getClassLoader().getResourceAsStream("people.txt");
        List<Person> people = parser().from(input).parse(Person.class);
        assertThat(people).hasSize(3);
        assertThat(people).containsExactly(
                new Person("WALTER", "WHITE"),
                new Person("JESSE", "PINKMAN"),
                new Person("SAUL", "GOODMAN")
        );
    }

    @Test
    public void should_stream_people_from_text_file() {
        InputStream input = ParserTest.class.getClassLoader().getResourceAsStream("people.txt");
        Stream<Person> peopleStream = parser().from(input).stream(Person.class);
        // Who is W.W. ? :-)
        List<Person> people = peopleStream.filter((p) -> p.getFirstname().charAt(0) == p.getLastname().charAt(0)).collect(toList());
        assertThat(people).hasSize(1);
        assertThat(people).containsExactly(new Person("WALTER", "WHITE"));
    }


    @Test
    public void should_parse_people_with_ids_from_text_file() {
        InputStream input = ParserTest.class.getClassLoader().getResourceAsStream("people_with_ids.txt");
        List<PersonWithId> people = parser().from(input).parse(PersonWithId.class);
        assertThat(people).hasSize(4);
        assertThat(people).containsExactly(
                new PersonWithId("1", "HEISENBERG                    ", "WALTER", "WHITE"),
                new PersonWithId("2", "                              ", "JESSE", "PINKMAN"),
                new PersonWithId("99", "                              ", "SAUL", "GOODMAN"),
                new PersonWithId("999", "                              ", "MARIE", "SCHRADER")
        );
    }
}
