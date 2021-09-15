package com.somosmas.app.service;

import com.somosmas.app.exception.custom.ContactAlreadyExistException;
import com.somosmas.app.model.entity.Contact;
import com.somosmas.app.model.request.ContactRequest;
import com.somosmas.app.model.response.ListContactResponse;
import com.somosmas.app.repository.IContactRepository;
import com.somosmas.app.service.abstraction.IContactService;
import com.somosmas.app.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private IContactRepository contactRepository;

    @Override
    public void create(ContactRequest request) throws ContactAlreadyExistException {
        if (contactRepository.existsByEmail(request.getEmail()))
            throw new ContactAlreadyExistException(request.getEmail());
        Contact contact = ConvertUtil.convertToEntity(request);
        contact.setSoftDelete(false);
        contactRepository.save(contact);
    }

    @Override
    public ListContactResponse list() {
        List<Contact> contacts = contactRepository.findAll();
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
