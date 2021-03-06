package org.parsemylegacy.examples;

import org.parsemylegacy.definition.Column;
import org.parsemylegacy.definition.Line;

import java.util.Arrays;
import java.util.Objects;

@Line(length = 100)
public class PersonWithFiller {

    @Column(from = 1, to = 30)
    private String firstname;

    @Column(from = 31, to = 60)
    private String lastname;

    public PersonWithFiller() {
    }

    public PersonWithFiller(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PersonWithFiller)) {
            return false;
        }
        PersonWithFiller person = (PersonWithFiller) obj;
        return Arrays.equals(
                new Object[]{firstname, lastname},
                new Object[]{person.firstname, person.lastname}
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

}
