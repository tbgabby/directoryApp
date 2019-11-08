package com.tb.directoryapp.converters;

import com.tb.directoryapp.contactForm.ContactForm;
import com.tb.directoryapp.domain.Contact;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ContactFormToContact implements Converter<ContactForm, Contact> {

    @Override
    public Contact convert(ContactForm contactForm) {
        Contact contact = new Contact();

        if (contactForm.getId() != null  && !StringUtils.isEmpty(contactForm.getId())) {
            contact.setId(contactForm.getId());
        }

        contact.setFirstName(contactForm.getFirstName());
        contact.setLastName(contactForm.getLastName());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setEmail(contactForm.getEmail());
//        contact.setPassWord(contactForm.getPassWord());

        return contact;
    }

}

