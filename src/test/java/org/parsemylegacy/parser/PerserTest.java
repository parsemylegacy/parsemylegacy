package org.parsemylegacy.parser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.parsemylegacy.parser.Parser.parser;

public class PerserTest {

    @Test
    public void should_create_parser_instance() {
        assertThat(parser()).isNotNull();
    }

}
