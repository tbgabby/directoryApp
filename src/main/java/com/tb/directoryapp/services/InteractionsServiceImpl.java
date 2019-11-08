package com.tb.directoryapp.services;

import com.tb.directoryapp.contactForm.ContactForm;
import com.tb.directoryapp.converters.ContactFormToInteractions;
import com.tb.directoryapp.domain.Interactions;
import com.tb.directoryapp.repositories.InteractionsRepository;
import org.springframework.stereotype.Service;


@Service
public class InteractionsServiceImpl implements InteractionsService {
    private InteractionsRepository interactionsRepository;
    private ContactFormToInteractions contactFormToInteractions;

    public InteractionsServiceImpl(InteractionsRepository interactionsRepository, ContactFormToInteractions contactFormToInteractions) {
        this.interactionsRepository = interactionsRepository;
        this.contactFormToInteractions = contactFormToInteractions;
    }

    @Override
    public Interactions saveOrUpdate(Interactions interactions) {
        return interactionsRepository.save(interactions);
    }

    @Override
    public Interactions saveOrUpdateContactForm(ContactForm contactForm) {

        Interactions savedContact = saveOrUpdate(contactFormToInteractions.convert(contactForm));

        System.out.println("***Saved Interactions as S/N: " + savedContact.getId() + " ***");
        return savedContact;
    }

}
