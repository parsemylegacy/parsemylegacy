package org.parsemylegacy.writer;

import java.io.*;
import java.util.List;

public class Writer<T> {

    private final Class<T> clazz;

    private String lineSeparator = System.getProperty("line.separator");

    public Writer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getLineSeparator() {
        return this.lineSeparator;
    }

    public Writer<T> withLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
        return this;
    }

    public void write(List<T> objects, OutputStream os) {
        LineWriter<T> lineWriter = new LineWriter<>(this.clazz);

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))) {
            for (T obj : objects) {
                bw.write(lineWriter.write(obj));
                bw.write(lineSeparator);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
