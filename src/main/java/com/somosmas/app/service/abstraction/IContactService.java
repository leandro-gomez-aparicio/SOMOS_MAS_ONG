package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.ContactAlreadyExistException;
import com.somosmas.app.model.request.ContactRequest;

public interface IContactService {
    void create (ContactRequest request) throws ContactAlreadyExistException;
}
