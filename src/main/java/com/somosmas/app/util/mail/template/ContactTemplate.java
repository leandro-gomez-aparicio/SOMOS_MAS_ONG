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

}
