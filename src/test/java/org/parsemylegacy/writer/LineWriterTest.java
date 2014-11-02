package org.parsemylegacy.writer;

import org.junit.Test;
import org.parsemylegacy.examples.PersonWithFiller;
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

    @Test
    public void should_write_line_with_filler() {
        PersonWithFiller person = new PersonWithFiller();
        person.setFirstname("WALTER");
        person.setLastname("WHITE");
        LineWriter<PersonWithFiller> lineWriter = new LineWriter<>(PersonWithFiller.class);
        String personLine = lineWriter.write(person);
        assertThat(personLine).isEqualTo("WALTER                        WHITE                                                                 ");
    }
}
