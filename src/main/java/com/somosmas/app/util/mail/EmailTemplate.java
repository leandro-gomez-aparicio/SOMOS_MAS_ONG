package com.somosmas.app.util.mail;

import java.io.Serializable;

public class EmailTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String logo;
    private final String organizationName;
    private final String welcomeMessage;
    private final ContactTemplate contact;

    public EmailTemplate(String logo, String organizationName, String welcomeMessage, ContactTemplate contact) {
        this.logo = logo;
        this.organizationName = organizationName;
        this.welcomeMessage = welcomeMessage;
        this.contact = contact;
    }

}
