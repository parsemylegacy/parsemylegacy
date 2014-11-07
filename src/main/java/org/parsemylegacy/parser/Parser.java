package org.parsemylegacy.parser;

import java.io.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Parser<T> {

    private final Class<T> clazz;

    public Parser(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> parse(InputStream input) {
        LineParser<T> lineParser = new LineParser<>(this.clazz);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            return reader
                    .lines()
                    .map(lineParser::parse)
                    .collect(toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
