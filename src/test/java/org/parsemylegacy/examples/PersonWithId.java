package org.parsemylegacy.examples;

import org.parsemylegacy.definition.Column;
import org.parsemylegacy.definition.Line;
import org.parsemylegacy.utils.TrimDirection;

import java.util.Arrays;
import java.util.Objects;

@Line
public class PersonWithId {

    @Column(from = 1, to = 3, trimCharacter = '#', trimDirection = TrimDirection.LEFT)
    private String id;

    @Column(from = 4, to = 33, trim = false)
    private String nickname;

    @Column(from = 34, to = 63)
    private String firstname;

    @Column(from = 64, to = 93)
    private String lastname;

    public PersonWithId() {
    }

    public PersonWithId(String id, String nickname, String firstname, String lastname) {
        this.id = id;
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        if (obj == null || !(obj instanceof PersonWithId)) {
            return false;
        }
        PersonWithId person = (PersonWithId) obj;
        return Arrays.equals(
                new Object[]{id, nickname, firstname, lastname},
                new Object[]{person.id, person.nickname, person.firstname, person.lastname}
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, firstname, lastname);
    }

}
