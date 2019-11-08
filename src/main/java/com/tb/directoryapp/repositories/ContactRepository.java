package com.tb.directoryapp.repositories;

import com.tb.directoryapp.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
