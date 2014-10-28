package org.parsemylegacy.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.parsemylegacy.utils.Strings.trim;

public class StringsTest {

    @Test
    public void should_trim_only_right_spaces() {
        assertThat(trim("  WALTER      ", ' ', TrimDirection.RIGHT)).isEqualTo("  WALTER");
    }

    @Test
    public void should_trim_only_left_spaces() {
        assertThat(trim("  WALTER      ", ' ', TrimDirection.LEFT)).isEqualTo("WALTER      ");
    }

    @Test
    public void should_trim_left_and_right_spaces() {
        assertThat(trim("  WALTER      ", ' ', TrimDirection.BOTH)).isEqualTo("WALTER");
    }

    @Test
    public void should_trim_only_right_plus_signs() {
        assertThat(trim("+++BLUE METH+++", '+', TrimDirection.RIGHT)).isEqualTo("+++BLUE METH");
    }

    @Test
    public void should_trim_only_left_plus_signs() {
        assertThat(trim("+++BLUE METH+++", '+', TrimDirection.LEFT)).isEqualTo("BLUE METH+++");
    }

    @Test
    public void should_trim_left_and_right_plus_signs() {
        assertThat(trim("+++BLUE METH+++", '+', TrimDirection.BOTH)).isEqualTo("BLUE METH");
    }
}
