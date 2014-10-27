package org.parsemylegacy.parser;

import org.junit.Test;
import org.parsemylegacy.examples.Person;

import java.io.InputStream;
import java.util.List;

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

}
