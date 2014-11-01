package org.parsemylegacy.examples;

import org.parsemylegacy.definition.Column;
import org.parsemylegacy.definition.Line;

@Line
public class UnsortedPerson {

    @Column(from = 34, to = 63)
    private String firstname;

    @Column(from = 1, to = 3)
    private String id;

    @Column(from = 64, to = 93)
    private String lastname;

    @Column(from = 4, to = 33)
    private String nickname;


}
