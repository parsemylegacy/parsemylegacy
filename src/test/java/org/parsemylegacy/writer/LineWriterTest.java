package org.parsemylegacy.writer;

import org.junit.Test;
import org.parsemylegacy.examples.PersonWithId;

import static org.assertj.core.api.Assertions.assertThat;

public class LineWriterTest {

    @Test
    public void should_write_line() throws Exception {
        PersonWithId person = new PersonWithId("1", "HEISENBERG", "WALTER", "WHITE");
        LineWriter<PersonWithId> lineWriter = new LineWriter<>(PersonWithId.class);
        String personLine = lineWriter.write(person);
        assertThat(personLine).isEqualTo("1  HEISENBERG                    WALTER                        WHITE                         ");
    }


    @Test
    public void should_write_line_with_null_values() throws Exception {
        PersonWithId person = new PersonWithId();
        person.setId("1");
        person.setFirstname("WALTER");
        LineWriter<PersonWithId> lineWriter = new LineWriter<>(PersonWithId.class);
        String personLine = lineWriter.write(person);
        assertThat(personLine).isEqualTo("1                                WALTER                                                      ");
    }

}
