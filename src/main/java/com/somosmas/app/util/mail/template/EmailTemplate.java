package com.somosmas.app.util.mail.template;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.util.mail.IContent;
import com.somosmas.app.util.mail.IEmail;

import java.io.Serializable;

public class EmailTemplate implements Serializable, IEmail {

    private static final long serialVersionUID = 1L;

    private final String logo;
    private final String organizationName;
    private final String welcomeMessage;
    private final ContentTemplate content;
    private final ContactTemplate contact;
    private final String fromEmail;
    private final String subject;

    public EmailTemplate(String logo,
                         String organizationName,
                         String welcomeMessage,
                         String subject,
                         String fromEmail,
                         ContactTemplate contact,
                         ContentTemplate content) {
        this.logo = logo;
        this.organizationName = organizationName;
        this.welcomeMessage = welcomeMessage;
        this.subject = subject;
        this.contact = contact;
        this.content = content;
        this.fromEmail = fromEmail;
    }

    public EmailTemplate(String subject, String fromEmail, ContactTemplate contact, ContentTemplate content) {
        this(null, null, null, subject, fromEmail, contact, content);
    }

    public EmailTemplate(String subject,
                         Organization organization,
                         ContactTemplate contact,
                         ContentTemplate content) {
        this(organization.getImage(),
                organization.getName(),
                organization.getWelcomeText(),
                subject,
                organization.getEmail(),
                contact,
                content);
    }
    @Override
    public String getTo() {
        return contact.getEmail();
    }

    @Override
    public String getFrom() {
        return fromEmail;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public IContent getContent() {
        return content;
    }

}
