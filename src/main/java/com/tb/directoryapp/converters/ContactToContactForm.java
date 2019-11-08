package com.tb.directoryapp.converters;

import com.tb.directoryapp.contactForm.ContactForm;
import com.tb.directoryapp.domain.Contact;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactForm implements Converter<Contact, ContactForm> {

    @Override
    public ContactForm convert(Contact contact) {
        ContactForm contactForm = new ContactForm();
        contactForm.setId(contact.getId());
        contactForm.setFirstName(contact.getFirstName());
        contactForm.setLastName(contact.getLastName());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setEmail(contact.getEmail());

        return contactForm;
    }
}
