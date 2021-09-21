package com.somosmas.app.util.mail.template;

import java.io.Serializable;

public class ContactTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String firstName;
    private final String lastName;
    private final String email;

    public ContactTemplate(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public ContactTemplate(String name, String email) {
        this(name,null,email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
