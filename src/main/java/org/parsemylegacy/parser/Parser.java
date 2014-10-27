package org.parsemylegacy.parser;

import java.io.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Parser {

    private InputStream input;

    public static Parser parser() {
        return new Parser();
    }

    public Parser from(InputStream input) {
        this.input = input;
        return this;
    }

    public <T> List<T> parse(Class<T> clazz) {
        if (this.input == null) {
            throw new IllegalArgumentException("No input provided! Use Parser#from(InputStream input)");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            return reader
                    .lines()
                    .map((line) -> LineParser.parse(clazz, line))
                    .collect(toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
