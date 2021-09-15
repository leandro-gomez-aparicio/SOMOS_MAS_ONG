package com.somosmas.app.model.response;

import java.util.List;

import com.somosmas.app.model.request.ContactRequest;

public class ListContactResponse {
	
    private List<ContactRequest> contacts;
    
    public ListContactResponse() {
    }

    public List<ContactRequest> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactRequest> contacts) {
        this.contacts = contacts;
    }
    
}
