package org.parsemylegacy.writer;

import org.junit.Test;
import org.parsemylegacy.examples.PersonWithId;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WriterTest {

    @Test
    public void should_write_lines() throws Exception {
        PersonWithId walter = new PersonWithId("1", "HEISENBERG", "WALTER", "WHITE");
        PersonWithId jesse = new PersonWithId("2", null, "JESSE", "PINKMAN");
        PersonWithId saul = new PersonWithId("3", null, "SAUL", "GOODMAN");
        List<PersonWithId> people = Arrays.asList(walter, jesse, saul);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer<PersonWithId> writer = new Writer<>(PersonWithId.class);
        writer.write(people, baos);

        assertThat(baos.toString())
                .isEqualTo("1  HEISENBERG                    WALTER                        WHITE                         "
                                + writer.getLineSeparator()
                                + "2                                JESSE                         PINKMAN                       "
                                + writer.getLineSeparator()
                                + "3                                SAUL                          GOODMAN                       "
                                + writer.getLineSeparator()
                );
    }

}
