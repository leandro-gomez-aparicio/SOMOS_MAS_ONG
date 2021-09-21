package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.ContactAlreadyExistException;
import com.somosmas.app.exception.custom.SendEmailException;
import com.somosmas.app.model.request.ContactRequest;
import com.somosmas.app.model.response.ListContactResponse;

public interface IContactService {

    void create(ContactRequest request) throws ContactAlreadyExistException, SendEmailException;

    ListContactResponse list();
}
