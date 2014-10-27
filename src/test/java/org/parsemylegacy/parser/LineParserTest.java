package org.parsemylegacy.parser;

import org.junit.Test;
import org.parsemylegacy.examples.Person;

import static org.assertj.core.api.Assertions.assertThat;

public class LineParserTest {

    @Test
    public void should_parse_line() throws Exception {
        String line = "WALTER                        WHITE                         ";
        Person person = LineParser.parse(Person.class, line);
        assertThat(person.getFirstname()).isEqualTo("WALTER");
        assertThat(person.getLastname()).isEqualTo("WHITE");
    }


}
