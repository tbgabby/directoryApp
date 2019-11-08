package com.tb.directoryapp.services;

import com.tb.directoryapp.contactForm.ContactForm;
import com.tb.directoryapp.domain.Interactions;


public interface InteractionsService {

    Interactions saveOrUpdate(Interactions interactions);

    Interactions saveOrUpdateContactForm(ContactForm contactForm);

}
