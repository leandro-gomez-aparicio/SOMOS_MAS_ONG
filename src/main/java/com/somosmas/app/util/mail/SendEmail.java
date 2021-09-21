package com.somosmas.app.util.mail;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.somosmas.app.exception.custom.SendEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SendEmail {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmail.class);
    private static final String SEND = "mail/send";
    @Value("${spring.sendgrid.api-key}")
    private String API_KEY;
    public void execute(IEmail email) throws SendEmailException {
        SendGrid sendGrid = new SendGrid(API_KEY);
        Request request = new Request();

        Email from = new Email(email.getFrom());
        Email to = new Email(email.getTo());
        Content content = new Content(email.getContent().getType(), email.getContent().getValue());
        Mail mail = new Mail(from, email.getSubject(), to, content);

        try {
            request.setMethod(Method.POST);
            request.setEndpoint(SEND);

            request.setBody(mail.build());

            Response response = sendGrid.api(request);
            LOGGER.debug(response.getBody());

            if (!(response.getStatusCode()>=200 || response.getStatusCode()<300)) {
                throw new SendEmailException();
            }
        } catch (Exception ex) {
            throw new SendEmailException();
        }
    }

}