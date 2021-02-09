package fr.bred.batchtotem.domain;

import java.io.Serializable;

public class NewPerson implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lastName;
    private String firstName;

    public NewPerson() {
    }

    public NewPerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName;
    }
}
