package com.tb.directoryapp.services;

import com.tb.directoryapp.contactForm.ContactForm;
import com.tb.directoryapp.domain.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> listAll();

    Contact getById(Long id);

    Contact saveOrUpdate(Contact contact);

    void delete(Long id);

    Contact saveOrUpdateContactForm(ContactForm contactForm);

}


