package com.somosmas.app.service;

import com.somosmas.app.exception.custom.ContactAlreadyExistException;
import com.somosmas.app.exception.custom.SendEmailException;
import com.somosmas.app.model.entity.Contact;
import com.somosmas.app.model.request.ContactRequest;
import com.somosmas.app.model.response.ListContactResponse;
import com.somosmas.app.repository.IContactRepository;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.service.abstraction.IContactService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.mail.SendEmail;
import com.somosmas.app.util.mail.template.ContactTemplate;
import com.somosmas.app.util.mail.template.ContentTemplate;
import com.somosmas.app.util.mail.template.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    private static final String TEXT_PLAIN = "text/plain";
    private static final String CONTACT_CREATED_EMAIL_BODY = "Thanks for contacting us we will be in touch with you shortly.";
    private static final String CONTACT_CREATED_EMAIL_SUBJECT = "Contact Received";

    @Autowired
    private IContactRepository contactRepository;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private IOrganizationRepository organizationRepository;

    @Override
    public void create(ContactRequest request) throws ContactAlreadyExistException, SendEmailException {
        if (contactRepository.existsByEmail(request.getEmail())) {
            throw new ContactAlreadyExistException(request.getEmail());
        }

        Contact contact = ConvertUtil.convertToEntity(request);
        contact.setSoftDelete(false);
        contactRepository.save(contact);

        String organizationEmail = organizationRepository.findAll().get(0).getEmail();
        ContentTemplate mailContent = new ContentTemplate(TEXT_PLAIN, CONTACT_CREATED_EMAIL_BODY);
        ContactTemplate mailContact = new ContactTemplate(contact.getName(), contact.getEmail());

        sendEmail.execute(new EmailTemplate(
                CONTACT_CREATED_EMAIL_SUBJECT,
                organizationEmail,
                mailContact,
                mailContent));
    }

    @Override
    public ListContactResponse list() {
        List<Contact> contacts = contactRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse();
        ListContactResponse response = new ListContactResponse();
        if (contacts.isEmpty()) {
            return response;
        }

        List<ContactRequest> contactRequest = new ArrayList<>();
        for (Contact contact : contacts) {
            ContactRequest contactsRequest = new ContactRequest();
            contactsRequest.setName(contact.getName());
            contactRequest.add(contactsRequest);
        }
        response.setContacts(contactRequest);
        return response;
    }

}
