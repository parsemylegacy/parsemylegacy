package org.parsemylegacy.examples;

import org.parsemylegacy.definition.Column;
import org.parsemylegacy.definition.Line;

@Line
public class Person {

    @Column(from = 1, to = 30)
    private String firstname;

    @Column(from = 31, to = 60)
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
